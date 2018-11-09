package com.itheima.dao;

import com.itheima.domain.SysLog;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectKey;

public interface LogDao {

    @SelectKey(keyProperty = "id",keyColumn = "id",before = true,resultType = Integer.class,statement = "select log_seq.nextval from dual")
    @Insert("insert into sys_log values(#{id},#{visitTime},#{username},#{ip},#{method})")
    void save(SysLog log);
}
