package dk.dd.rabbit;
/*
 * Message Producer
 *
 * Produces a simple message, which will be delivered to a specific consumer
 * 1) Creates a queue
 * 2) Sends the message to it
 */

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RabbitApplication {
    private final static String QUEUE_NAME = "my_queue";
    private final static String EXCHANGE_NAME = "rabbit@emil";
    private final static String ROUTING_KEY = "midnight";

    public static void main(String[] args) throws Exception {
        SpringApplication.run(RabbitApplication.class, args);
        String message = "Det virker stadig!";
        createQueue(message);
        System.out.println(" [x] Sent '" + message + "'");
    }

    public static void createQueue(String message) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
//                channel.queueDeclare(QUEUE_NAME, false, false, false, null);
//                channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, message.getBytes("UTF-8"));
            channel.exchangeDeclare(EXCHANGE_NAME, "direct", true);
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY);
            channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes("UTF-8"));

        }
    }
}