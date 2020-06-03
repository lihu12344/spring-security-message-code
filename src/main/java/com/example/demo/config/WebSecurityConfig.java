package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.annotation.Resource;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private MessageCodeSecurityConfig messageCodeSecurityConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/login/form").and().authorizeRequests()
                .antMatchers("/hello").hasAuthority("ROLE_USER")
                .antMatchers("/**").permitAll();

        http.apply(messageCodeSecurityConfig);
    }
}
