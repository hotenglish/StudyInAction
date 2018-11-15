package spittr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import com.icegreen.greenmail.spring.GreenMailBean;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Arrays;

@Configuration
@ComponentScan("spittr")
@PropertySource("classpath:mail.properties")
public class MailConfig {

    @Bean
    public GreenMailBean greenEmail(){
        GreenMailBean greenMailBean=new GreenMailBean();
        greenMailBean.setUsers(Arrays.asList("app:letmein01@spitter.com"));
        return greenMailBean;
    }

    @Bean
    public JavaMailSender mailSender(Environment env){
        JavaMailSenderImpl javaMailSender=new JavaMailSenderImpl();
        javaMailSender.setHost(env.getProperty("mailserver.host"));
        javaMailSender.setPort(Integer.parseInt(env.getProperty("mailserver.port")));
        javaMailSender.setUsername(env.getProperty("mailserver.username"));
        javaMailSender.setPassword(env.getProperty("mailserver.password"));
        return javaMailSender;
    }

}
