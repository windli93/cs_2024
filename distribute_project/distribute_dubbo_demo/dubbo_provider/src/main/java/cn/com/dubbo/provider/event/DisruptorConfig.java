package cn.com.dubbo.provider.event;

import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.util.DaemonThreadFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DisruptorConfig {
    @Bean
    public Disruptor<TypedEvent<?>> disruptor(DisruptorEventDispatcher dispatcher,
                                              OrderEventHandler orderHandler,   // 自定义处理器
                                              PaymentEventHandler paymentHandler // 自定义处理器
    ) {
        // 使用 Object 类型的工厂（实际通过 TypedEvent 携带类型）
        Disruptor<TypedEvent<?>> disruptor = new Disruptor<>(
            () -> new TypedEvent<>(),
            1024,
            DaemonThreadFactory.INSTANCE,
            ProducerType.MULTI,
            new YieldingWaitStrategy()
        );

        // 注册处理器
        dispatcher.registerHandler(OrderEvent.class, orderHandler);
        dispatcher.registerHandler(PaymentEvent.class, paymentHandler);

        disruptor.handleEventsWith(dispatcher);
        return disruptor;
    }
}