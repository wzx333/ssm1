package com.itheima.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/*
* responsebody:以流的形式返回字符串,以Jason的形式
* */
@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/showUsername")
    @ResponseBody
    public String showUsername(HttpServletRequest request){
        /*HttpSession session = request.getSession();
        Object o = session.getAttribute("SPRING_SECURITY_CONTEXT");
        //转换为securityContext类型的对象
        SecurityContext context = (SecurityContext)o;
        //通过上下文对象获取认证信息
        Authentication authentication = context.getAuthentication();
        //获取用户对象:principal:重要的
        Object principal = authentication.getPrincipal();
        User user = (User) principal;*/
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        User user = (User) authentication.getPrincipal();
        return user.getUsername();

    }
}
