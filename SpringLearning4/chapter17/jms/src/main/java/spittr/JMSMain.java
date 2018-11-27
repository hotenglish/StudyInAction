package spittr;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsOperations;

public class JMSMain {

    public static void main(String... args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/messaging.xml");
        JmsOperations jmsOperations = context.getBean(JmsOperations.class);
        for (int i = 0; i < 0; i++) {
            jmsOperations.convertAndSend("hello.queue", "Hello");
        }
        context.close();
    }

}
