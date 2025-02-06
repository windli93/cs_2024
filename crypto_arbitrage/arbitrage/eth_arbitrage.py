import json
import time
import math
import networkx as nx
from web3 import Web3, HTTPProvider
from web3.middleware import geth_poa_middleware
import smtplib
from email.mime.text import MIMEText

# 配置参数
CONFIG = {
    "INFURA_URL": "wss://mainnet.infura.io/ws/v3/YOUR_KEY",  # 改用WebSocket
    "WALLET_ADDRESS": "0xYourWalletAddress",
    "PRIVATE_KEY": "YOUR_ENCRYPTED_KEY",  # 实际应使用加密存储
    "AAVE_POOL": "0x7d2768dE32b0b80b7a3454c06BdAc94A69DDc7A9",
    "MIN_PROFIT_RATIO": 2.0,
    "ALERT_EMAIL": "your@email.com",
    "GAS_SAFETY_MARGIN": 1.3
}

# 初始化Web3
w3 = Web3(Web3.WebsocketProvider(CONFIG["INFURA_URL"]))
w3.middleware_onion.inject(geth_poa_middleware, layer=0)

# 初始化有向图
token_graph = nx.DiGraph()

# 加载合约ABI
with open('aave_abi.json') as f:
    AAVE_ABI = json.load(f)

# --- 新增模块1：MEV保护 ---
class MEVProtector:
    @staticmethod
    def build_eip1559_tx():
        base_fee = w3.eth.get_block('pending')['baseFeePerGas']
        return {
            'type': '0x2',
            'maxPriorityFeePerGas': int(base_fee * 0.15),
            'maxFeePerGas': int(base_fee * 1.15)
        }

# --- 新增模块2：多路径检测 ---
class ArbitragePathFinder:
    def __init__(self):
        self.token_pairs = self.load_top_pairs()

    def load_top_pairs(self):
        # 从链上加载前50交易对
        return [
            ("ETH", "USDC"), ("USDC", "DAI"),
            ("ETH", "WBTC"), ("USDT", "ETH")
        ]

    def update_graph(self):
        for pair in self.token_pairs:
            reserves = self.get_pair_reserves(*pair)
            price = reserves[1]/reserves[0] if reserves[0] != 0 else 0
            fee_adjusted_price = price * 0.997  # 考虑手续费
            token_graph.add_edge(
                pair[0], pair[1],
                weight=-math.log(fee_adjusted_price),
                liquidity=sum(reserves)
            )

    def find_arbitrage_paths(self):
        paths = []
        try:
            # 使用Bellman-Ford算法检测负权重循环
            for node in token_graph.nodes:
                path = nx.bellman_ford_path(token_graph, node)
                if len(path) > 2 and path[0] == path[-1]:
                    paths.append(path)
            return paths[:3]  # 返回前3个最佳路径
        except nx.NetworkXNoCycle:
            return []

# --- 新增模块3：闪电贷集成 ---
class FlashLoanExecutor:
    def __init__(self):
        self.aave = w3.eth.contract(
            address=CONFIG["AAVE_POOL"],
            abi=AAVE_ABI
        )

    def prepare_loan(self, amount, asset):
        # 构建闪电贷交易（需要自定义回调合约）
        # 此处为简化示例，实际需要部署回调合约
        calldata = self._build_calldata(amount, asset)
        return self.aave.functions.flashLoan(
            callbackContract,
            [asset],
            [amount],
            [0],
            callbackContract,
            calldata,
            0
        ).buildTransaction()

# --- 新增模块4：监控与警报 ---
class BlockMonitor:
    def __init__(self):
        self.last_block = w3.eth.block_number

    def start_monitoring(self):
        while True:
            latest_block = w3.eth.block_number
            if latest_block > self.last_block:
                self.process_block(latest_block)
                self.last_block = latest_block
            time.sleep(0.5)

    def process_block(self, block_num):
        block = w3.eth.get_block(block_num, full_transactions=True)
        for tx in block.transactions:
            if tx.to == CONFIG["UNISWAP_V2_ROUTER"]:
                self.analyze_arbitrage(tx)

class AlertSystem:
    @staticmethod
    def send_alert(message):
        msg = MIMEText(message)
        msg['Subject'] = '套利系统警报'
        msg['From'] = CONFIG["ALERT_EMAIL"]
        msg['To'] = CONFIG["ALERT_EMAIL"]

        with smtplib.SMTP('smtp.example.com', 587) as server:
            server.starttls()
            server.login('user', 'pass')
            server.send_message(msg)

# --- 改进的交易执行器 ---
class EnhancedArbitrageExecutor:
    def __init__(self):
        self.path_finder = ArbitragePathFinder()
        self.flash_loan = FlashLoanExecutor()

    def execute_safe_arbitrage(self, path):
        try:
            # 步骤1：路径可行性验证
            if not self.validate_path(path):
                return False

            # 步骤2：利润计算（含Gas校验）
            gross_profit, gas_cost = self.calculate_profit(path)
            if gross_profit < gas_cost * CONFIG["MIN_PROFIT_RATIO"]:
                return False

            # 步骤3：选择资金源（自有或闪电贷）
            if gross_profit > 1e18:  # 超过1 ETH使用闪电贷
                tx_data = self.flash_loan.prepare_loan(...)
            else:
                tx_data = self.build_standard_tx(...)

            # 步骤4：发送交易并监控
            tx_hash = self.send_transaction(tx_data)
            return self.monitor_transaction(tx_hash)

        except Exception as e:
            AlertSystem.send_alert(f"交易失败: {str(e)}")
            return False

    def validate_path(self, path):
        # 验证路径流动性
        for i in range(len(path)-1):
            edge = token_graph[path[i]][path[i+1]]
            if edge['liquidity'] < 1e6:  # 流动性阈值
                return False
        return True

# --- 主执行流程 ---
if __name__ == "__main__":
    monitor = BlockMonitor()
    executor = EnhancedArbitrageExecutor()

    # 启动区块监听线程
    import threading
    monitor_thread = threading.Thread(target=monitor.start_monitoring)
    monitor_thread.daemon = True
    monitor_thread.start()

    # 主循环处理套利机会
    while True:
        try:
            # 更新市场状态
            executor.path_finder.update_graph()

            # 查找所有潜在路径
            paths = executor.path_finder.find_arbitrage_paths()

            # 按路径利润排序
            ranked_paths = sorted(
                paths,
                key=lambda x: executor.calculate_profit(x)[0],
                reverse=True
            )

            # 执行最佳路径
            if ranked_paths:
                executor.execute_safe_arbitrage(ranked_paths[0])

            time.sleep(5)  # 每5秒扫描一次

        except KeyboardInterrupt:
            print("系统安全退出")
            break
        except Exception as e:
            AlertSystem.send_alert(f"系统异常: {str(e)}")
            time.sleep(60)  # 错误冷却时间