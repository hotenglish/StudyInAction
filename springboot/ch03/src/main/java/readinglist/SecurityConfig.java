package readinglist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Profile("production")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final static Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    @Autowired
    private ReaderRepository readerRepository;

    @Autowired
    InitializingApplicationData initializingApplicationDatal;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        initializingApplicationDatal.insertData();
        http.csrf().disable();
        http
            .authorizeRequests()
            .antMatchers("/h2-console").permitAll()
            .antMatchers("/h2-console/**").permitAll()
            .antMatchers("/").access("hasRole('READER')")
            .antMatchers("/**").permitAll()
            .and()
            .formLogin()
            .loginPage("/login")
            .failureUrl("/login?error=true");
    }

    @Override
    protected void configure(
                AuthenticationManagerBuilder auth) throws Exception {
        auth
          .userDetailsService(new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username)
                  throws UsernameNotFoundException {
                UserDetails userDetails = readerRepository.findOne(username);
                logger.info("username:"+username+" userDetials:" + userDetails);
                if (userDetails != null) {
                    return userDetails;
                }
                logger.info("Throw User '" + username + "' not found.");
                throw new UsernameNotFoundException("User '" + username + "' not found.");
            }
        });
    }

}
