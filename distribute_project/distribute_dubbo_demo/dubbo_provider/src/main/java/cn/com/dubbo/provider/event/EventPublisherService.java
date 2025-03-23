package cn.com.dubbo.provider.event;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventPublisherService {
    @Autowired
    private RingBuffer<TypedEvent<Object>> ringBuffer;

    public <T> void publishEvent(Class<Object> eventType, T data) {
        // 1. 创建类型明确的翻译器
        EventTranslatorOneArg<TypedEvent<Object>, T> translator =
                new TypedEventTranslator<>(eventType);

        // 2. 直接发布（无需手动操作 RingBuffer）
        ringBuffer.publishEvent(translator, data);
    }
}