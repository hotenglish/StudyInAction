package com.elson.dao;

import org.apache.ibatis.annotations.Param;
import com.elson.pojo.Role;

public interface RoleDao {
    int insertRole(@Param("role") Role role);
}
