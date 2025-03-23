package cn.com.dubbo.provider.event;

public class OrderEvent {
    private String orderId;
    // Getter/Setter 省略


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}