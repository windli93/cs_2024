package cn.com.dubbo.provider.event;

public class TypedEvent<T> {
    private Class<T> eventType;  // 保留泛型类型信息（避免擦除）

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private T data;              // 泛型业务数据

    // Getter/Setter 省略


    public Class<T> getEventType() {
        return eventType;
    }

    public void setEventType(Class<T> eventType) {
        this.eventType = eventType;
    }
}