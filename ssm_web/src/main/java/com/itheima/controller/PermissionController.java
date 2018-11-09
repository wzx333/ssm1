package com.itheima.controller;

import com.itheima.domain.Permission;
import com.itheima.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
@Secured({"ROLE_ADMIN"})
public class PermissionController {
    @Autowired
    PermissionService permissionService;
    @RequestMapping("/findAll")
    public ModelAndView findAll(){
       List<Permission> permissionList = permissionService.findAll();
       ModelAndView modelAndView = new ModelAndView();
       modelAndView.addObject("permissionList",permissionList);
       modelAndView.setViewName("permission-list");
       return modelAndView;
    }

    @RequestMapping("/saveUI")
    public ModelAndView saveUI(){
        ModelAndView modelAndView = new ModelAndView();
        List<Permission> permissionList = permissionService.findByPId();
        System.out.println(permissionList);
        modelAndView.addObject("permissionList",permissionList);
        modelAndView.setViewName("permission-add");
        return modelAndView;
    }
    @RequestMapping("/save")
    public String  save(Permission permission){
        permissionService.save(permission);
        return "redirect:/permission/findAll";
    }
}
