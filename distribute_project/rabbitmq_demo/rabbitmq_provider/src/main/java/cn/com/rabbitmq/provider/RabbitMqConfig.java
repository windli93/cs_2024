package cn.com.rabbitmq.provider;

import cn.com.rabbitmq.config.Constants;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author hongjian.li
 * @Description
 * @Date 3/1/2025 4:13 PM
 **/
@Configuration
public class RabbitMqConfig {


    //队列 起名：TestDirectQueue
    @Bean
    public Queue TestDirectQueue() {
        // durable:是否持久化,默认是false,持久化队列：会被存储在磁盘上，当消息代理重启时仍然存在，暂存队列：当前连接有效
        // exclusive:默认也是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
        // autoDelete:是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除。
        //   return new Queue("TestDirectQueue",true,true,false);

        //一般设置一下队列的持久化就好,其余两个就是默认false
        return new Queue(Constants.TestDirectQueue, true);
    }

    @Bean
    public Queue TestFantonQueue() {
        // durable:是否持久化,默认是false,持久化队列：会被存储在磁盘上，当消息代理重启时仍然存在，暂存队列：当前连接有效
        // exclusive:默认也是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
        // autoDelete:是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除。
        //   return new Queue("TestDirectQueue",true,true,false);

        //一般设置一下队列的持久化就好,其余两个就是默认false
        return new Queue(Constants.TestFantonQueue, true);
    }

    @Bean
    public Queue TestTestTopicQueue() {
        // durable:是否持久化,默认是false,持久化队列：会被存储在磁盘上，当消息代理重启时仍然存在，暂存队列：当前连接有效
        // exclusive:默认也是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
        // autoDelete:是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除。
        //   return new Queue("TestDirectQueue",true,true,false);

        //一般设置一下队列的持久化就好,其余两个就是默认false
        return new Queue(Constants.TestTopicQueue, true);
    }


    @Bean
    public Queue TestDeadQueue() {
        Map<String, Object> arguments = new HashMap<>();
        //死信交换机
        arguments.put("x-dead-letter-exchange", "");
        //死信路由Key
        arguments.put("x-dead-letter-routing-key", "");
        //长度限制
        arguments.put("x-max-length","");
        //过期时间
        arguments.put("x-message-ttl","");
        return new Queue(Constants.TestTopicQueue, true, false, false, arguments);
    }

    //Direct类型
    @Bean
    DirectExchange TestDirectExchange() {
        //  return new DirectExchange("TestDirectExchange",true,true);
        return new DirectExchange(Constants.TestDirectExchange, true, false);
    }

    //广播类型
    @Bean
    FanoutExchange TestFanoutExchange() {
        //  return new DirectExchange("TestDirectExchange",true,true);
        return new FanoutExchange(Constants.TEST_FANTON_EXCHANGE, true, false);
    }

    //Topic类型
    @Bean
    TopicExchange TestTopicExchange() {
        //  return new DirectExchange("TestDirectExchange",true,true);
        return new TopicExchange(Constants.TEST_TOPIC_EXCHANGE, true, false);
    }

    //绑定  将队列和交换机绑定, 并设置用于匹配键：TestDirectRouting
    @Bean
    Binding bindingDirect() {
        return BindingBuilder.bind(TestDirectQueue())
                .to(TestDirectExchange()).with(Constants.TestDirectRouting);
    }

    @Bean
    Binding bindingFanton() {
        return BindingBuilder.bind(TestFantonQueue())
                .to(TestFanoutExchange());
    }

    @Bean
    Binding bindingTopic1() {
        return BindingBuilder
                .bind(TestTestTopicQueue())
                .to(TestTopicExchange()).with(Constants.TestTopicRouting1);
    }

    @Bean
    Binding bindingTopic2() {
        return BindingBuilder
                .bind(TestTestTopicQueue())
                .to(TestTopicExchange()).with(Constants.TestTopicRouting2);
    }

    @Bean
    Binding bindingTopic3() {
        return BindingBuilder
                .bind(TestTestTopicQueue())
                .to(TestTopicExchange()).with(Constants.TestTopicRouting3);
    }
}
