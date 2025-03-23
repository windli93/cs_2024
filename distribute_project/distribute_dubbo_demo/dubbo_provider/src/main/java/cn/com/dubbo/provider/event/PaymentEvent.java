package cn.com.dubbo.provider.event;

public class PaymentEvent {
    private String paymentId;
    // Getter/Setter 省略


    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }
}