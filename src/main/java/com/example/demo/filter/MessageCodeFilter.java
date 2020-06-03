package com.example.demo.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

@Component
public class MessageCodeFilter implements HandlerInterceptor {

    private List<String> messageList= Arrays.asList("123456","654321");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String message=request.getParameter("phone");

        if (!messageList.contains(message)){
            System.out.println("手机号不存在");
            response.getWriter().write("手机号不存在");

            return false;
        }else {
            return true;
        }
    }
}
