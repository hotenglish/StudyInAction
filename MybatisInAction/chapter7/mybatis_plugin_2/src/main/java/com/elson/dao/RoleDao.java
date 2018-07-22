package com.elson.dao;

import com.elson.pojo.Role;
import org.apache.ibatis.annotations.Param;

public interface RoleDao {
    int insertRole(@Param("role") Role role);
}
