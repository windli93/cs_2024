package cn.com.rabbitmq.consumer;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author hongjian.li
 * @Description
 * @Date 3/1/2025 4:13 PM
 **/
@Configuration
public class RabbitMqConfig {
    // 声明队列
    @Bean
    public Queue myQueue() {
        return new Queue("myQueue", true); // durable=true（持久化）
    }

    // 声明直连交换机
    @Bean
    public DirectExchange myExchange() {
        return new DirectExchange("myExchange");
    }

    // 绑定队列到交换机
    @Bean
    public Binding binding(Queue myQueue, DirectExchange myExchange) {
        return BindingBuilder.bind(myQueue)
                .to(myExchange)
                .with("myRoutingKey");
    }
}
