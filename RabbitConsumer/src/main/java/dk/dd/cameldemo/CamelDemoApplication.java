package dk.dd.cameldemo;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CamelDemoApplication
{
    public static List<Topic> topics = new ArrayList<Topic>() {{
        add(Topic.SKI);
        add(Topic.TROPICAL);
    }};
    public static void main(String[] args)
    {
        // todo: Maybe add some way to dynamically add topics
        // Scanner input = new Scanner(System.in);

        SpringApplication.run(CamelDemoApplication.class, args);
    }

}
