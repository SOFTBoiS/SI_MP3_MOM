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

    private final static String[] QUEUES = {"skiing", "autumn", "mediterranean", "tropical"};
    private final static String EXCHANGE_NAME = "travel_campaigns";

    public static void main(String[] args) throws Exception {
        SpringApplication.run(RabbitApplication.class, args);
//        String message = "skiing Hello we have good skiing offer for you. Januar 1-7 austria 100kr";
        createQueue();

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("============================");
            for (int i = 0; i<QUEUES.length; i++){
                System.out.println(i + ":" + QUEUES[i]);
            }

            System.out.println("Enter number to select topic:");
            String topic = QUEUES[sc.nextInt()];
            sc.nextLine(); // Consume the line where we got the int from

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
            channel.queueDeclare(QUEUES[0], true, false, false, null);
            channel.queueDeclare(QUEUES[1], true, false, false, null);
            channel.queueDeclare(QUEUES[2], true, false, false, null);
            channel.queueDeclare(QUEUES[3], true, false, false, null);


            channel.queueBind(QUEUES[0], EXCHANGE_NAME, QUEUES[0]);
            channel.queueBind(QUEUES[1], EXCHANGE_NAME, QUEUES[1]);
            channel.queueBind(QUEUES[2], EXCHANGE_NAME, QUEUES[2]);
            channel.queueBind(QUEUES[3], EXCHANGE_NAME, QUEUES[3]);

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