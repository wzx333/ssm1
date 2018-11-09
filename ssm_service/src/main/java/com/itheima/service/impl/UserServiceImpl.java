package com.itheima.service.impl;

import com.itheima.dao.UserDao;
import com.itheima.domain.Role;
import com.itheima.domain.SysUser;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = userDao.findByUsername(username);
        if(sysUser!=null) {
            //设置一个假的角色列表
            List<GrantedAuthority> authorities = new ArrayList<>();
            List<Role> roleList = sysUser.getRoleList();
            for (Role role : roleList) {
                //security后台会自动为每一个角色添加前缀：ROLE_ 所以即使这里和数据库内前面不加ROlE也会访问到
                SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role.getRoleName());
                authorities.add(simpleGrantedAuthority);
            }
            //创建假的角色
            UserDetails userDetails = new User(sysUser.getUsername(),sysUser.getPassword(),authorities);
            return userDetails;
        }
        return null;
    }

    @Override
    public List<SysUser> findAll() {
        return userDao.findAll();
    }

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public void save(SysUser sysUser) {
        String enpassword = bCryptPasswordEncoder.encode(sysUser.getPassword());
        sysUser.setPassword(enpassword);
        userDao.save(sysUser);
    }

    @Override
    public SysUser findByUsername(String username) {
        return userDao.findByUsernameisUniqueName(username);
    }

    @Override
    public SysUser findById(Integer id) {
        return userDao.findById(id);
    }

    @Override
    public void addRoleToUser(Integer uid, Integer[] ids) {
        userDao.deleteByUid(uid);
        if(ids!=null&&ids.length>0){
            for (Integer id : ids) {
                userDao.saveRoleToUser(uid,id);
            }
        }
    }
}
