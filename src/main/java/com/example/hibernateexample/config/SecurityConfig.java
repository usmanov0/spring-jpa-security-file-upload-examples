package com.example.hibernateexample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;

    public SecurityConfig(@Lazy UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

                //static data
//                .inMemoryAuthentication()
//                .withUser("usmanov").password(passwordEncoder().encode("Azizbek")).roles("ADMIN")
//                .and()
//                .withUser("user").password(passwordEncoder().encode("1234")).roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .headers()
                .frameOptions()
                .disable()
                .and()
                .authorizeRequests()
                .antMatchers("/api/posts").hasRole("USER")
                //.antMatchers("api/role").authenticated()
                .antMatchers("api/user-register").permitAll()
                .antMatchers("/api/posts/pageable/**").hasRole("ADMIN")
                .antMatchers("api/retrieve-posts").hasAnyRole("USER","ADMIN")
//                .antMatchers("/api/posts/**").denyAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }

        @Bean
        PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
