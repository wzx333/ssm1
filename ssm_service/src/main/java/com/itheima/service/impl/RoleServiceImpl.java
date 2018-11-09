package com.itheima.service.impl;

import com.itheima.dao.RoleDao;
import com.itheima.domain.Role;
import com.itheima.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleDao roleDao;
    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    @Override
    public Role findRoleById(Integer id) {
        return roleDao.findByRid(id);
    }

    @Override
    public void addPermissionToRole(Integer roleId, Integer[] ids) {
        roleDao.deleteById(roleId);
        if(ids!=null&&ids.length>0){
            for (Integer id : ids) {
                roleDao.addPermissionToRole(id,roleId);
            }
        }
    }
}
