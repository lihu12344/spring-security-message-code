package com.example.demo.security;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MessageCodeAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private String MessageCodeParameter="phone";

    private boolean postOnly = true;

    public MessageCodeAuthenticationFilter(){
        super(new AntPathRequestMatcher("/message/login", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        if (this.postOnly && !httpServletRequest.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + httpServletRequest.getMethod());
        }else {
            String phone=httpServletRequest.getParameter(MessageCodeParameter);

            if (phone==null){
                phone="";
            }

            phone=phone.trim();
            MessageCodeAuthenticationToken authRequest=new MessageCodeAuthenticationToken(phone);
            this.setDetails(httpServletRequest,authRequest);

            return this.getAuthenticationManager().authenticate(authRequest);
        }
    }

    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    protected void setDetails(HttpServletRequest request, MessageCodeAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }

    public void setMessageCodeParameter(String messageCodeParameter){
        Assert.hasText(messageCodeParameter, "MessageCode parameter must not be empty or null");
        this.MessageCodeParameter=messageCodeParameter;
    }

    public String getMessageCodeParameter() {
        return MessageCodeParameter;
    }
}
