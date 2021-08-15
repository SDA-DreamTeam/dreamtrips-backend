package com.github.dreamteam.configuration;

import com.github.dreamteam.repository.UserRepository;
import com.github.dreamteam.security.BearerAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.session.jdbc.JdbcIndexedSessionRepository;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;

@Configuration
@EnableWebSecurity
@EnableJdbcHttpSession
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private JdbcIndexedSessionRepository sessionRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        BearerAuthenticationFilter filter = new BearerAuthenticationFilter(
                sessionRepository,
                userRepository
        );
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/countries").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/countries").permitAll()
                .antMatchers("/sign-in").permitAll()
                .and()
                .anonymous()
                .and()
                .csrf().disable()
                .addFilterBefore(filter, FilterSecurityInterceptor.class);
    }

    @Bean
    public PasswordEncoder getPasswordEndcoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
