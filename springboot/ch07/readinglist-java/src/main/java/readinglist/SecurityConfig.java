package readinglist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final static Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    @Autowired
    private ReaderRepository readerRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        InitializingApplicationData.toInitializingApplicationData(readerRepository);
        http.csrf().disable();
        http
                .authorizeRequests()
                .antMatchers("/").access("hasRole('READER')")
                .antMatchers("/mgmt/**").access("hasRole('ADMIN')")
                .antMatchers("/mgmt/**").access("hasRole('ACTUATOR')")
                .antMatchers("/**").permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error=true");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(new UserDetailsService() {
                @Override
                public UserDetails loadUserByUsername(String username)
                        throws UsernameNotFoundException {
                    UserDetails user = readerRepository.findOne(username);
                    logger.info("username:" + username + " userDetials:" + user);
                    if (user != null) {
                        return user;
                    }
                    logger.info("Throw User '" + username + "' not found.");
                    throw new UsernameNotFoundException("User '" + username + "' not found.");
                }
            })
            .and()
            .inMemoryAuthentication()
            .withUser("admin").password("good").roles("ADMIN", "READER", "ACTUATOR");
    }

}