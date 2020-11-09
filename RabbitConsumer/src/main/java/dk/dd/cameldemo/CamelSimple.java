package dk.dd.cameldemo;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class CamelSimple extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        MyTransformation myTransform = new MyTransformation();
        if (CamelDemoApplication.topics.contains(Topic.SKI)) {

            from("rabbitmq:localhost:5672/rabbit@adam?username=guest&password=guest&routingKey=midnight&autoDelete=false?topic=winter")
                    .log("Transformed message is ${body}")
                    .bean(myTransform, "toUpper").bean(myTransform, "getLength")
                    .log("Transformed message is ${body}")
                    .to("file:src/main/resources/file").end();
        }
        if (CamelDemoApplication.topics.contains(Topic.TROPICAL)) {
            from("rabbitmq:localhost:5672/rabbit@adam?username=guest&password=guest&routingKey=midnight&autoDelete=false?topic=summer")
                    .log("Transformed message is ${body}")
                    .bean(myTransform, "toUpper").bean(myTransform, "getLength")
                    .log("Transformed message is ${body}")
                    .to("file:src/main/resources/file").end();
        }


    }

}
