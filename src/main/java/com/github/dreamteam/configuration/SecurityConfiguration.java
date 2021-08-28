package com.github.dreamteam.configuration;

import com.github.dreamteam.repository.UserRepository;
import com.github.dreamteam.security.BearerAuthenticationFilter;
import com.github.dreamteam.service.AuthService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.session.jdbc.JdbcIndexedSessionRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter implements BeanFactoryAware {

    private BeanFactory beanFactory;

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        DaoAuthenticationProvider authenticationProvider = daoAuthenticationProvider();
        auth.authenticationProvider(authenticationProvider).userDetailsService(authService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        JdbcIndexedSessionRepository sessionRepository = beanFactory.getBean(JdbcIndexedSessionRepository.class);
        BearerAuthenticationFilter filter = new BearerAuthenticationFilter(
                sessionRepository,
                userRepository
        );
        http.cors().and().authorizeRequests()
                .antMatchers(HttpMethod.POST, "/countries").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/countries").permitAll()
                .antMatchers(HttpMethod.POST, "/cities").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/cities").permitAll()
                .antMatchers(HttpMethod.POST, "/airports").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/airports").permitAll()
                .antMatchers(HttpMethod.POST, "/hotels").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/hotels").permitAll()
                .antMatchers(HttpMethod.POST, "/trips").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/trips").permitAll()
                .antMatchers(HttpMethod.POST, "/bookings").permitAll()
                .antMatchers(HttpMethod.GET, "/bookings").permitAll()
                .antMatchers("/registration").permitAll()

                .antMatchers("/sign-in").permitAll()
                .and()
                .anonymous()
                .and()
                .csrf().disable()
                .addFilterBefore(filter, FilterSecurityInterceptor.class);
    }

    @Bean
    public PasswordEncoder passwordEndcoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEndcoder());
        provider.setUserDetailsService(authService);
        return provider;
    }
}
