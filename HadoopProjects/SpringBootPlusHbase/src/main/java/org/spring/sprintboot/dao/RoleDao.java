package org.spring.sprintboot.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;
import org.spring.sprintboot.domain.Role;

import java.util.List;
import java.util.Map;

@Mapper
public interface RoleDao {

    Role getRole(Long id);

    int saveOrUpdate(Role role);

    void deleteRole(Long id);

    void removeRoleFromUser(Map map);

    void addRoleForUser(Map map);

    List<Role> findRoleByUserId(Long userId);

    List<Role> findRolesByName(String roleName, RowBounds rowBounds);

}

