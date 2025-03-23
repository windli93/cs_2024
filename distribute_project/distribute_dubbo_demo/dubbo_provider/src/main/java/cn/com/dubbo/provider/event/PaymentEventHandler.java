package cn.com.dubbo.provider.event;

import org.springframework.stereotype.Component;

@Component
public class PaymentEventHandler implements TypedEventHandler<PaymentEvent> {
    @Override
    public void onEvent(PaymentEvent data) {
        System.out.println("处理支付: " + data.getPaymentId());
    }
}