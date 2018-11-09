package com.itheima.controller;

import com.itheima.domain.Permission;
import com.itheima.domain.Role;
import com.itheima.service.PermissionService;
import com.itheima.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
@Secured({"ROLE_ADMIN"})
public class RoleController {

    @Autowired
    RoleService roleService;

    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        List<Role> roleList = roleService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("roleList",roleList);
        modelAndView.setViewName("role-list");
        return modelAndView;
    }

    @RequestMapping("/save")
    public String save(Role role){
        roleService.save(role);
        return "redirect:/role/findAll";
    }


    @Autowired
    PermissionService permissionService;
    @RequestMapping("/findAllByRid")
    public ModelAndView findAllByRid(Integer rid){
        ModelAndView modelAndView = new ModelAndView();
        List<Permission> permissionList = permissionService.findAll();
        Role role = roleService.findRoleById(rid);
        List<Permission> rolePermissionList = role.getPermissionList();
        StringBuffer stringBuffer = new StringBuffer();
        for (Permission permission : rolePermissionList) {
            stringBuffer.append(",");
            stringBuffer.append(permission.getId());
            stringBuffer.append(",");
        }
        modelAndView.addObject("permissionList",permissionList);
        modelAndView.addObject("roleId",role.getId());
        modelAndView.addObject("stringBufferPr",stringBuffer.toString());
        modelAndView.setViewName("role-permission-add");
        return modelAndView;
    }
    @RequestMapping("/addPermissionToRole")
    public String addPermissionToRole(Integer roleId,Integer[] ids){
        roleService.addPermissionToRole(roleId,ids);
        return "redirect:/role/findAll";
    }
}
