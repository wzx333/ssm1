package com.itheima.dao;

import com.itheima.domain.SysUser;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
@Repository
public interface UserDao {
    @Select("select * from sys_user where username = #{username} and status = 1")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleList", column = "id", javaType = List.class, many = @Many(select = "com.itheima.dao.RoleDao.findByUid", fetchType = FetchType.LAZY))
    })
    SysUser findByUsername(String username);

    @Select("select * from sys_user")
    List<SysUser> findAll();

    /*
    * private Long id;
    private String username;
    private String email;
    private String password;
    private String phoneNum;
    private Integer status;
    *
    * */

    @Insert("insert into sys_user values(user_seq.nextval,#{username},#{email},#{password},#{phoneNum},#{status})")
    void save(SysUser sysUser);

    @Select("select * from sys_user where username = #{username}")
    SysUser findByUsernameisUniqueName(String username);

    @Select("select * from sys_user where id =#{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleList", column = "id", javaType = List.class, many = @Many(select = "com.itheima.dao.RoleDao.findByUid", fetchType = FetchType.LAZY))
    })
    SysUser findById(Integer id);

    @Delete("delete from sys_user_role where userid =#{uid}")
    void deleteByUid(Integer uid);

    @Insert("insert into sys_user_role values(#{param1},#{param2})")
    void saveRoleToUser(Integer uid,Integer id);
}
