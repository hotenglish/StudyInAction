package consumer;

import com.demo.pojo.TbUser;
import com.demo.user.service.UserService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Consumer {

    @Test
    public void testConsumer() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"dubbo-demo-consumer.xml"});
        context.start();
        UserService userService = (UserService) context.getBean("userService");
        try {
            List<TbUser> userList = userService.findUserList();
            userList.forEach(user -> {
                System.out.println(user);
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

}
