package cn.com.rabbitmq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class MessageConsumer {

    public static final String TestDirectQueue = "TestDirectQueue";
    public static final String TestDirectExchange = "TestDirectExchange";
    public static final String TestDirectRouting = "TestDirectRouting";

    @RabbitListener(queues = TestDirectQueue)
    @RabbitHandler
    public void receiver(@Payload HashMap message) {
        log.info("receiver 消费者1号 收到消息  --- message:{}", message);
    }

    @RabbitListener(queues = TestDirectQueue)
    @RabbitHandler
    public void receiver2(Map testMessage) {
        log.info("receiver2 消费者2号 收到消息 getClass:{}  ---  {}", testMessage.getClass(), testMessage);
    }
}