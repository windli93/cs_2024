# arbitrage_async.py
import asyncio
import math
from typing import List, Tuple, Optional
from decimal import Decimal
from pydantic import BaseModel, Field
from web3 import Web3, AsyncHTTPProvider
from web3.eth import AsyncEth
from aiohttp import ClientSession
from networkx import DiGraph,bellman_ford_predecessor_and_distance

class PoolConfig(BaseModel):
    exchange: str
    token0: str
    token1: str
    fee: Decimal = Field(..., gt=0)
    reserves0: int
    reserves1: int

class ArbitrageBot:
    def __init__(self, node_url: str):
        self.w3 = Web3(
            AsyncHTTPProvider(node_url),
            modules={"eth": (AsyncEth,)},
            middlewares=[]
        )
        self.graph = DiGraph()
        self.session: Optional[ClientSession] = None

    async def initialize(self):
        """异步初始化资源"""
        self.session = ClientSession()
        await self.w3.eth.get_block('latest')  # 测试连接

    async def fetch_pools_data(self, pairs: List[Tuple[str, str]]):
        """并发获取多个交易对数据"""
        async def fetch_pair(pair):
            contract = self.w3.eth.contract(
                address=Web3.to_checksum_address(pair[0]),
                abi="UNISWAP_V2_POOL_ABI"
            )
            reserves = await contract.functions.getReserves().call()
            return PoolConfig(
                exchange="Uniswap V2",
                token0=pair[0],
                token1=pair[1],
                fee=Decimal("0.003"),
                reserves0=reserves[0],
                reserves1=reserves[1]
            )

        tasks = [fetch_pair(pair) for pair in pairs]
        return await asyncio.gather(*tasks)

    def build_arbitrage_graph(self, pools: List[PoolConfig]):
        """构建套利机会图"""
        self.graph.clear()
        for pool in pools:
            price = pool.reserves1 / pool.reserves0 if pool.reserves0 else 0
            adj_price = price * (1 - float(pool.fee))

            self.graph.add_edge(
                pool.token0,
                pool.token1,
                weight=-math.log(adj_price),
                liquidity=pool.reserves0 + pool.reserves1
            )
            self.graph.add_edge(
                pool.token1,
                pool.token0,
                weight=-math.log(1/adj_price),
                liquidity=pool.reserves1 + pool.reserves0
            )

    async def find_arbitrage_cycles(self) -> List[List[str]]:
        """使用 Bellman-Ford 算法查找负权环"""
        for source in self.graph.nodes:
            try:
                predecessors, distances = bellman_ford_predecessor_and_distance(self.graph, source)
                for node in self.graph.nodes:
                    for neighbor in self.graph.neighbors(node):
                        if distances[node] + self.graph[node][neighbor]['weight'] < distances[neighbor]:
                            # 发现负环，回溯路径
                            cycle = [neighbor]
                            while cycle[-1] not in cycle[:-1]:
                                cycle.append(predecessors[cycle[-1]][0])
                            cycle.reverse()
                            return [cycle]
            except:
                continue
        return []

    async def execute_arbitrage(self, path: List[str], amount: int):
        """执行异步套利交易"""
        if not self.session:
            raise RuntimeError("Bot not initialized")

        # 使用EIP-1559交易格式
        latest_block = await self.w3.eth.get_block('latest')
        base_fee = latest_block['baseFeePerGas']

        tx_params = {
            'type': '0x2',
            'chainId': 1,
            'maxPriorityFeePerGas': int(base_fee * 1.1),
            'maxFeePerGas': int(base_fee * 1.3),
            'nonce': await self.w3.eth.get_transaction_count(self.w3.eth.default_account),
        }

        # 构建交易数据（示例使用1inch聚合路由）
        async with self.session.get(
                "https://api.1inch.io/v5.0/1/swap",
                params={
                    "fromTokenAddress": path[0],
                    "toTokenAddress": path[-1],
                    "amount": amount,
                    "fromAddress": self.w3.eth.default_account,
                    "slippage": 1
                }
        ) as resp:
            swap_data = await resp.json()

        signed_tx = await self.w3.eth.account.sign_transaction({
            **tx_params,
            'to': Web3.to_checksum_address(swap_data['tx']['to']),
            'data': swap_data['tx']['data'],
            'value': int(swap_data['tx']['value'])
        })

        return await self.w3.eth.send_raw_transaction(signed_tx.rawTransaction)

    async def monitor_opportunities(self, interval: int = 5):
        """持续监控套利机会"""
        while True:
            try:
                pairs = await self.fetch_top_pairs()
                pools = await self.fetch_pools_data(pairs)
                self.build_arbitrage_graph(pools)

                if cycles := await self.find_arbitrage_cycles():
                    for cycle in cycles[:3]:  # 处理前3个最佳机会
                        tx_hash = await self.execute_arbitrage(cycle, 1e18)
                        print(f"Arbitrage executed: {tx_hash.hex()}")

                await asyncio.sleep(interval)
            except Exception as e:
                print(f"监控异常: {str(e)}")
                await asyncio.sleep(interval * 2)

async def main():
    bot = ArbitrageBot("https://mainnet.infura.io/v3/YOUR_KEY")
    await bot.initialize()

    # 加载初始交易对
    top_pairs = [
        ("0xC02aaA39b223FE8D0A0e5C4F27eAD9083C756Cc2", "0xA0b86991c6218b36c1d19D4a2e9Eb0cE3606eB48"),  # WETH-USDC
        ("0xA0b86991c6218b36c1d19D4a2e9Eb0cE3606eB48", "0xdAC17F958D2ee523a2206206994597C13D831ec7"),  # USDC-USDT
    ]

    # 启动监控任务
    monitor_task = asyncio.create_task(bot.monitor_opportunities())

    try:
        await monitor_task
    except asyncio.CancelledError:
        print("监控任务终止")
    finally:
        await bot.session.close()

if __name__ == "__main__":
    asyncio.run(main())