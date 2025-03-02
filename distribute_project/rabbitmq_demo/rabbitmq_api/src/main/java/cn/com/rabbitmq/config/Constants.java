package cn.com.rabbitmq.config;

/**
 * @Author hongjian.li
 * @Description
 * @Date 3/1/2025 4:42 PM
 **/
public interface Constants {

    public static final String TestDirectQueue = "TestDirectQueue";
    public static final String TestDirectExchange = "TestDirectExchange";
    public static final String TestDirectRouting = "TestDirectRouting";

    public static final String TEST_FANTON_EXCHANGE = "TestFantonExchange";
    public static final String TestFantonQueue = "TestFantonQueue";

    public static final String TestConfirmExchange = "TestConfirmExchange";
    public static final String TestConfirmRouting1 = "test.confirm.routing.*";

    public static final String TEST_TOPIC_EXCHANGE = "TestTopicExchange";
    public static final String TestTopicQueue = "TestTopicQueue";

    public static final String TestTopicRouting1 = "topic.routing.*";
    public static final String TestTopicRouting2 = "topic.routing.#";
    public static final String TestTopicRouting3 = "topic.msg.*";

    public static final String TEST_DEAD_EXCHANGE = "x-dead-letter-exchange";
    public static final String TEST_DEAD_ROUTING_KEY = "x-dead-letter-routing-key";

}
