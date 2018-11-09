package com.itheima.dao;

import com.itheima.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionDao {

    @Select("select * from sys_permission")
    public List<Permission> findAll();

    @Select("select * from sys_permission where pid =0")
    List<Permission> findByPId();

    @Insert("insert into sys_permission values(permission_seq.nextval,#{permissionName},#{url},#{pid})")
    void save(Permission permission);

    @Select("select sp.* from sys_permission sp,sys_role_permission srp where srp.permissionid = sp.id and srp.roleid = #{rid}")
    List<Permission> findByRid(Integer rid);

}
