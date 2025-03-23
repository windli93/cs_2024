package cn.com.dubbo.provider.event;

public interface TypedEventHandler<T> {
    void onEvent(T data);
}