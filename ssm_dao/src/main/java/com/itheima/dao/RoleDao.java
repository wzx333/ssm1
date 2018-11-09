package com.itheima.dao;

import com.itheima.domain.Role;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface RoleDao {

    @Select("select * from sys_role")
    List<Role> findAll();

    @Insert("insert into sys_role values(#{id},#{roleName},#{roleDesc})")
    @SelectKey(keyProperty = "id",keyColumn = "id",before = true,resultType = Integer.class,
    statement = "select role_seq.nextval from dual")
    void save(Role role);

    @Select("select sr.* from sys_role sr,sys_user_role sur where sr.id = sur.roleid and sur.userid = #{uid}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "permissionList",column = "id",javaType =List.class,
                    many = @Many(select = "com.itheima.dao.PermissionDao.findByRid",fetchType = FetchType.LAZY))
    })
    List<Role> findByUid(Integer uid);


    @Select("select * from sys_role where id = #{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "permissionList",column = "id",javaType = List.class,
                    many = @Many(select = "com.itheima.dao.PermissionDao.findByRid",fetchType = FetchType.LAZY))
    })
    Role findByRid(Integer id);

    @Delete("delete from sys_role_permission where roleid = #{roleId}")
    void deleteById(Integer roleId);

    @Insert("insert into sys_role_permission values(#{param1},#{param2})")
    void addPermissionToRole(Integer id,Integer rid);
}
