package com.itheima.log;

import com.itheima.domain.SysLog;
import com.itheima.service.LogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/*
* joinpoint:执行的方法对象
*
*
*
* */
@Controller
@Aspect
public class logController {

    @Autowired
    HttpServletRequest request;

    @Autowired
    LogService logService;

    @Pointcut("execution(* com.itheima.controller.*.*(..))")
    public void pointcut(){}

    @Before("pointcut()")
    public void before(JoinPoint joinPoint){
        SysLog log = new SysLog();
        log.setVisitTime(new Date());
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        User user = (User) authentication.getPrincipal();
        log.setUsername(user.getUsername());
        //ip地址从request对象中获取
        String ip = request.getRemoteAddr();
        log.setIp(ip);
        //获取方法对象
        Signature signature = joinPoint.getSignature();
        //获取方法名
        String methodName = signature.getName();
        //获取权限类名
        String className = joinPoint.getTarget().getClass().getName();
        log.setMethod(className+"."+methodName);
        //把日志对象添加到数据库中
        logService.save(log);
    }

}
