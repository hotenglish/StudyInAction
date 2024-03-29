package concert;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan
public class ConcertConfig {

    @Bean
    public Audience audience() {
        return new Audience();
    }

    @Bean
    public Performance actor(){
        return new Actor();
    }

    @Bean
    public Performance actress(){
        return new Actress();
    }

    @Bean
    public EncoreableIntroducer encoreableIntroducer(){
        return new EncoreableIntroducer();
    }

}
