package com.elson.chapter4.parameter.dao;

import com.elson.chapter4.parameter.pojo.Role;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface RoleDao {
    List<Role> findRoleByMap(Map<String,String> params);
    List<Role> findRoleByAnnotation(@Param("roleName") String rolename,@Param("note") String note);
}
