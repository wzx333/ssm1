package com.itheima.service;

import com.itheima.domain.SysUser;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<SysUser> findAll();

    void save(SysUser sysUser);

    SysUser findByUsername(String username);

    SysUser findById(Integer id);

    void addRoleToUser(Integer uid, Integer[] ids);
}
