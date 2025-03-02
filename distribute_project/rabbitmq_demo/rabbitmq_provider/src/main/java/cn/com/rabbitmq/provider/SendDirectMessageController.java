package cn.com.rabbitmq.provider;

import cn.binarywang.tools.generator.ChineseAddressGenerator;
import cn.binarywang.tools.generator.ChineseNameGenerator;
import cn.com.rabbitmq.config.Constants;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@RestController
@RequestMapping("/demo")
public class SendDirectMessageController {

    @Autowired
    RabbitTemplate rabbitTemplate;  //使用RabbitTemplate,这提供了接收/发送等等方法

    @GetMapping("/sendDirectMessage")
    public Map<String, Object> sendDirectMessage() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = StrUtil.format("hello 我叫：{} 我住在：{}", ChineseNameGenerator.getInstance().generate(),
                ChineseAddressGenerator.getInstance()
                        .generate());
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", DateUtil.now());
        //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
        rabbitTemplate.convertAndSend(Constants.TestDirectExchange, Constants.TestDirectRouting, map);
        return map;
    }


    /**
      *@Author hongjian.li
      *@Description 消息投入到交换机才会触发confirm
      **/
    @GetMapping("/sendConfirmMessage")
    public void sendConfirmMessage() {
        //定义回调
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                System.out.println("confirm方法被执行了");
                if (ack) {
                    System.out.println("接收消息成功了");
                } else {
                    System.out.println("接受失败消息");
                }
            }
        });

        //发送消息
        rabbitTemplate.convertAndSend(Constants.TestConfirmExchange, Constants.TestConfirmRouting1, "我在测试发送信息");
    }

/**
  *@Author hongjian.li
  *@Description  消息发送到Exchang后，Exchangeg路由到Queue失败，才会执行ReturnBack
  **/
    @GetMapping("/sendReturnMessage")
    public void sendReturnMessage() {

        //设置Return Back
        rabbitTemplate.setReturnsCallback(new RabbitTemplate.ReturnsCallback() {
            @Override
            public void returnedMessage(ReturnedMessage returnedMessage) {
                System.out.println("Return 执行了...............");
            }
        });

        //发送消息
        rabbitTemplate.convertAndSend(Constants.TestConfirmExchange, Constants.TestConfirmRouting1, "我在测试发送信息");
    }

    /**
      *@Author hongjian.li
      *@Description 设置ttl信息
      **/
    @GetMapping("/setTtlMessage")
    public void setTtlMessage() {
        //1. 队列统一设置时间 x-message-ttl
        //2. 消息单独设置过期时间 expiration, 当该消息到达队列顶端时，会单独判断
        //3. 两个都设置了，以时间短的那个为准
        //4. 消息过期，只有队列顶端的消息，才会移除

        //单独消息过期时间，用MessagePostProcessor处理
        new MessagePostProcessor(){
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                return null;
            }
        };

        //发送消息
//        rabbitTemplate.convertAndSend();
    }
}
