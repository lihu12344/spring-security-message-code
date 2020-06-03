package com.example.demo.config;

import com.example.demo.security.MessageCodeAuthenticationFailureHandler;
import com.example.demo.security.MessageCodeAuthenticationFilter;
import com.example.demo.security.MessageCodeAuthenticationProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

@Configuration
public class MessageCodeSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Resource
    private MessageCodeAuthenticationProvider messageCodeAuthenticationProvider;

    @Resource
    private MessageCodeAuthenticationFailureHandler messageCodeAuthenticationFailureHandler;

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        MessageCodeAuthenticationFilter messageCodeAuthenticationFilter=new MessageCodeAuthenticationFilter();
        messageCodeAuthenticationFilter.setAuthenticationManager(builder.getSharedObject(AuthenticationManager.class));
        messageCodeAuthenticationFilter.setAuthenticationFailureHandler(messageCodeAuthenticationFailureHandler);

        builder.authenticationProvider(messageCodeAuthenticationProvider)
                .addFilterAfter(messageCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
