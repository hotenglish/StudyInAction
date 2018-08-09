package com.elson.chapter2.mapper;

import com.elson.chapter2.po.Role;

public interface RoleMapper {

    Role getRole(Long id);

    int deleteRole(Long id);

    int insertRole(Role role);

}
