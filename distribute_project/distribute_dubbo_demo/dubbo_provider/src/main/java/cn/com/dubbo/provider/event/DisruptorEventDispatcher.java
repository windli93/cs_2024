package cn.com.dubbo.provider.event;

import com.lmax.disruptor.EventHandler;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class DisruptorEventDispatcher implements EventHandler<TypedEvent<?>> {
    private final Map<Class<?>, TypedEventHandler<?>> handlerRegistry = new ConcurrentHashMap<>();

    // 注册处理器
    public <T> void registerHandler(Class<T> eventType, TypedEventHandler<T> handler) {
        handlerRegistry.put(eventType, handler);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onEvent(TypedEvent<?> event, long sequence, boolean endOfBatch) {
        Class<?> eventType = event.getEventType();
        TypedEventHandler<Object> handler = (TypedEventHandler<Object>) handlerRegistry.get(eventType);
        if (handler != null) {
            handler.onEvent(event.getData());
        }
    }
}