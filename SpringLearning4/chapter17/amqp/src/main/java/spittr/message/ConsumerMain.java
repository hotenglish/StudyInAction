package spittr.message;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;

public class ConsumerMain {

    public static void main(String args[]) {
        ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/amqp-consumer.xml");
    }

}
