package cn.com.dubbo.provider.event;

import com.lmax.disruptor.EventFactory;

public class TypedEventFactory<T> implements EventFactory<TypedEvent<T>> {
    private final Class<T> eventType;

    public TypedEventFactory(Class<T> eventType) {
        this.eventType = eventType;
    }

    @Override
    public TypedEvent<T> newInstance() {
        TypedEvent<T> event = new TypedEvent<>();
        event.setEventType(eventType);
        return event;
    }
}