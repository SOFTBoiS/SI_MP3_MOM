package dk.dd.cameldemo;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class CamelSimple extends RouteBuilder {
    public List<Topic> topics = new ArrayList<Topic>();
    public static String username = "";



    @Override
    public void configure() throws Exception {
        subscribeToTopics();
        MyTransformation myTransform = new MyTransformation();
        if (topics.contains(Topic.SKIING)) {

            from("rabbitmq:localhost:5672/travel_campaigns?username=guest&password=guest&autoDelete=false&exchangeType=topic&queue=" + Topic.SKIING.toString().toLowerCase())
                    .bean(myTransform, "offer")
                    .choice()
                    .when(bodyAs(String.class).contains("declined"))
                    .log("Offer declined")
                    .otherwise()
                    .log("Offer accepted")
                    .to("file:src/main/resources/orders");
        }
        if (topics.contains(Topic.TROPICAL)) {

            from("rabbitmq:localhost:5672/travel_campaigns?username=guest&password=guest&autoDelete=false&exchangeType=topic&queue=" + Topic.TROPICAL.toString().toLowerCase())
                    .bean(myTransform, "offer")
                    .choice()
                    .when(bodyAs(String.class).contains("declined"))
                    .log("Offer declined")
                    .otherwise()
                    .log("Offer accepted")
                    .to("file:src/main/resources/orders");
        }
        if (topics.contains(Topic.MEDITERRANEAN)) {

            from("rabbitmq:localhost:5672/travel_campaigns?username=guest&password=guest&autoDelete=false&exchangeType=topic&queue=" + Topic.MEDITERRANEAN.toString().toLowerCase())
                    .bean(myTransform, "offer")
                    .choice()
                    .when(bodyAs(String.class).contains("declined"))
                    .log("Offer declined")
                    .otherwise()
                    .log("Offer accepted")
                    .to("file:src/main/resources/orders");
        }
        if (topics.contains(Topic.AUTUMN)) {

            from("rabbitmq:localhost:5672/travel_campaigns?username=guest&password=guest&autoDelete=false&exchangeType=topic&queue=" + Topic.AUTUMN.toString().toLowerCase())
                    .bean(myTransform, "offer")
                    .choice()
                    .when(bodyAs(String.class).contains("declined"))
                    .log("Offer declined")
                    .otherwise()
                    .log("Offer accepted")
                    .to("file:src/main/resources/orders");
        }

    }

    private void subscribeToTopics(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Select a username");
        // todo: handle invalid username
        username = sc.next();
        System.out.println("Select topics you wish to subscribe to");

        for (int i = 0; i < Topic.values().length; i++) {
            String choice = "";

            while (!choice.equals("yes") && !choice.equals("no")) {
                System.out.println("Do you wish to subscribe to " + Topic.values()[i] + " yes/no");
                choice = sc.next();
            }

            if (choice.equals("yes")) {
                topics.add(Topic.values()[i]);
            }
        }
        System.out.println("Listening for selected topics..");
    }
}
