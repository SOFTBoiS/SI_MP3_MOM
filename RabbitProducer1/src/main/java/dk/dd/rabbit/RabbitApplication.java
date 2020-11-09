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

import java.util.Scanner;

@SpringBootApplication
public class RabbitApplication {
    private final static String QUEUE_SKIING = "skiing";
    private final static String QUEUE_AUTUMN = "autumn";
    private final static String QUEUE_MEDITERRANEAN = "mediterranean";
    private final static String QUEUE_TROPICAL = "tropical";
    private final static String EXCHANGE_NAME = "travel_campaigns";

    public static void main(String[] args) throws Exception {
        SpringApplication.run(RabbitApplication.class, args);
//        String message = "skiing Hello we have good skiing offer for you. Januar 1-7 austria 100kr";
        createQueue();

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Enter topic name:");
            String topic = sc.nextLine();
            System.out.println("Enter offer:");
            String offer = sc.nextLine();

            sendMessage(topic, offer);
            System.out.println(" [x] Sent '" + offer + "'");

        }
    }

    public static void createQueue() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
//                channel.queueDeclare(QUEUE_NAME, false, false, false, null);
//                channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, message.getBytes("UTF-8"));
            channel.exchangeDeclare(EXCHANGE_NAME, "topic", true);
            channel.queueDeclare(QUEUE_SKIING, true, false, false, null);
            channel.queueDeclare(QUEUE_AUTUMN, true, false, false, null);
            channel.queueDeclare(QUEUE_MEDITERRANEAN, true, false, false, null);
            channel.queueDeclare(QUEUE_TROPICAL, true, false, false, null);


            channel.queueBind(QUEUE_SKIING, EXCHANGE_NAME, QUEUE_SKIING);
            channel.queueBind(QUEUE_AUTUMN, EXCHANGE_NAME, QUEUE_AUTUMN);
            channel.queueBind(QUEUE_MEDITERRANEAN, EXCHANGE_NAME, QUEUE_MEDITERRANEAN);
            channel.queueBind(QUEUE_TROPICAL, EXCHANGE_NAME, QUEUE_TROPICAL);

//            channel.basicPublish(EXCHANGE_NAME, routingKey, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes("UTF-8"));
        }
    }

    public static void sendMessage(String routingKey, String message) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            channel.basicPublish(EXCHANGE_NAME, routingKey, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes("UTF-8"));

        }
    }

}