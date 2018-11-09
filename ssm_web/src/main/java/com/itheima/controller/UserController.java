package com.itheima.controller;

import com.itheima.dao.UserDao;
import com.itheima.domain.Role;
import com.itheima.domain.SysUser;
import com.itheima.service.RoleService;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
@Secured({"ROLE_ADMIN"})
public class UserController {

    @Autowired
    UserService userService;
    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        ModelAndView modelAndView = new ModelAndView();
        List<SysUser> userList = userService.findAll();
        modelAndView.addObject("userList",userList);
        modelAndView.setViewName("user-list");
        return modelAndView;
    }

    @RequestMapping("/findById")
    public ModelAndView findById(Integer id){
        SysUser user = userService.findById(id);
        System.out.println(user);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user",user);
        modelAndView.setViewName("user-show");
        return modelAndView;
    }


    @RequestMapping("/save")
    public String save(SysUser sysUser){
        userService.save(sysUser);
        return "redirect:/user/findAll";
    }

    @RequestMapping("/isUniqueName")
    @ResponseBody
    public String isUniqueName(String username){
        SysUser sysUser = userService.findByUsername(username);
        if(sysUser!=null){
            return "false";
        }else {
            return "true";
        }
    }

    @Autowired
    RoleService roleService;

    @RequestMapping("/findAllById")
    public ModelAndView findAllById(Integer id){
        List<Role> roleList = roleService.findAll();
        SysUser sysUser = userService.findById(id);
        List<Role> sysUserRoleList = sysUser.getRoleList();
        StringBuffer stringBuffer = new StringBuffer();
        for (Role role : sysUserRoleList) {
            stringBuffer.append(",");
            stringBuffer.append(role.getId());
            stringBuffer.append(",");
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("roleList",roleList);
        modelAndView.addObject("stringBuffer",stringBuffer.toString());
        modelAndView.addObject("userId",sysUser.getId());
        modelAndView.setViewName("user-role-add");
        return modelAndView;
    }

    @RequestMapping("/addRoleToUser")
    public String addRoleToUser(Integer userId,Integer[] ids){
       userService.addRoleToUser(userId,ids);

        return "redirect:/user/findAll";
    }
}
