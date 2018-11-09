package com.itheima.service;

import com.itheima.domain.Permission;

import java.util.List;

public interface PermissionService {
    public List<Permission> findAll();

    List<Permission> findByPId();

    void save(Permission permission);
}
