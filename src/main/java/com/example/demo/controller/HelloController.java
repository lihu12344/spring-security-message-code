package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello(Principal principal){
        return "hello "+principal.getName();
    }

    @RequestMapping("/message/code")
    public Map<String,String> messageCode(HttpServletRequest request){
        String phone=request.getParameter("phone");
        String code=generateCode();
        System.out.println("code："+code);

        Map<String,String> map=new HashMap<>();
        map.put("phone",phone);
        map.put("code",code);

        request.getSession().setAttribute("message_code",map);

        return map;
    }

    private String generateCode(){
        StringBuilder buffer=new StringBuilder();
        Random r=new Random();

        for (int i=0;i<6;i++){
            buffer.append(r.nextInt(10));
        }

        return buffer.toString();
    }
}
