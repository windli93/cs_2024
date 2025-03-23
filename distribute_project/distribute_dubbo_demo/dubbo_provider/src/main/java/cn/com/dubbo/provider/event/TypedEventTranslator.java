package cn.com.dubbo.provider.event;

import com.lmax.disruptor.EventTranslatorOneArg;

public class TypedEventTranslator<T> implements EventTranslatorOneArg<TypedEvent<Object>, T> {
    private final Class<Object> eventType;

    public TypedEventTranslator(Class<Object> eventType) {
        this.eventType = eventType;
    }

    @Override
    public void translateTo(TypedEvent<Object> event, long sequence, T data) {
        event.setEventType(eventType);  // Class<T> 赋值给 Class<?>（合法）
        event.setData(data);            // T 赋值给 Object（自动向上转型）
    }
}