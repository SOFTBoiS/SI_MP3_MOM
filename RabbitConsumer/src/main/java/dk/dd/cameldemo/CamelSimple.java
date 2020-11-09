package dk.dd.cameldemo;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class CamelSimple extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        MyTransformation myTransform = new MyTransformation();
        if (CamelDemoApplication.topics.contains(Topic.SKIING)) {

            from("rabbitmq:localhost:5672/ad.campaign?username=guest&password=guest&autoDelete=false&exchangeType=topic&queue=" + Topic.SKIING.toString().toLowerCase())
                    .log("${body}")
                    .bean(myTransform, "offer")
                    .choice()
                    .when(bodyAs(String.class).contains("declined"))
                    .log("Offer declined")
                    .otherwise()
                    .to("file:src/main/resources/orders");
        }
        if (CamelDemoApplication.topics.contains(Topic.TROPICAL)) {

            from("rabbitmq:localhost:5672/ad.campaign?username=guest&password=guest&autoDelete=false&exchangeType=topic&queue=" + Topic.TROPICAL.toString().toLowerCase())
                    .log("${body}")
                    .bean(myTransform, "offer")
                    .choice()
                    .when(bodyAs(String.class).contains("declined"))
                    .log("Offer declined")
                    .otherwise()
                    .to("file:src/main/resources/orders");
        }
        if (CamelDemoApplication.topics.contains(Topic.MEDITERRANEAN)) {

            from("rabbitmq:localhost:5672/ad.campaign?username=guest&password=guest&autoDelete=false&exchangeType=topic&queue=" + Topic.MEDITERRANEAN.toString().toLowerCase())
                    .log("${body}")
                    .bean(myTransform, "offer")
                    .choice()
                    .when(bodyAs(String.class).contains("declined"))
                    .log("Offer declined")
                    .otherwise()
                    .to("file:src/main/resources/orders");
        }
        if (CamelDemoApplication.topics.contains(Topic.AUTUMN)) {

            from("rabbitmq:localhost:5672/ad.campaign?username=guest&password=guest&autoDelete=false&exchangeType=topic&queue=" + Topic.AUTUMN.toString().toLowerCase())
                    .log("${body}")
                    .bean(myTransform, "offer")
                    .choice()
                    .when(bodyAs(String.class).contains("declined"))
                    .log("Offer declined")
                    .otherwise()
                    .to("file:src/main/resources/orders");
        }

    }
}
