package cn.com.dubbo.provider.event;

import org.springframework.stereotype.Component;

@Component
public class OrderEventHandler implements TypedEventHandler<OrderEvent> {
    @Override
    public void onEvent(OrderEvent data) {
        System.out.println("处理订单: " + data.getOrderId());
    }
}