package cn.com.rabbitmq.consumer;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author hongjian.li
 * @Description 1. 设置手动签收，acknowledge "manual"
 * 2. 设置监听类ChannelAwareMessageListener接口
 * 3. 如果消息成功处理，则调用channel的basicAck()方法
 * 4. 如果消息调用失败，则调用channel的basicNack()方法，broker会重新发送
 **/
@Slf4j
@Component
public class MessageAckConsumer implements ChannelAwareMessageListener {

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();

        try {
            //接收转换消息
            System.out.println(new String(message.getBody()));

            //处理业务逻辑
            System.out.println("处理业务逻辑");
            //消息确认
            channel.basicAck(deliveryTag, true);
        } catch (Exception e) {
            //消息失败，则放到死信队列
            channel.basicNack(deliveryTag, true, true);
        }
    }
}