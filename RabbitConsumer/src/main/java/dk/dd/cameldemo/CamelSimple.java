package dk.dd.cameldemo;

import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class CamelSimple extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        MyTransformation myTransform = new MyTransformation();
        if (CamelDemoApplication.topics.contains(Topic.SKI)) {

            from("rabbitmq:localhost:5672/ad.campaign?username=guest&password=guest&autoDelete=false&exchangeType=topic&queue=skiing")
                    .log("${body}")
                    .bean(myTransform, "offer")
                    .choice()
                        .when(bodyAs(String.class).contains("pass"))
                            .to("file:src/main/resources/orders")
                        .otherwise()
                            .log("Offer declined")
                            .end();
        }
        if (CamelDemoApplication.topics.contains(Topic.TROPICAL)) {
            from("rabbitmq:localhost:5672/ad.campaign?username=guest&password=guest&autoDelete=false&&exchangeType=topic&queue=tropical")
                    .log("${body}")
                    .bean(myTransform, "offer")
                    .choice()
                        .when(bodyAs(String.class).contains("pass"))
                            .to("file:src/main/resources/orders")
                        .otherwise()
                            .log("Offer declined")
                            .end();
        }
    }
}
