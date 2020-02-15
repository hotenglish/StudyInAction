package org.spring.sprintboot.dao;

import org.apache.ibatis.session.RowBounds;
import org.spring.sprintboot.domain.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRowMapper {

    Role getRole(Long id);

    int saveOrUpdate(Role role);

    void deleteUser(Long id);

    List<Role> findRoleByUserId(Long userId);

    List<Role> findRolesByName(String roleName, RowBounds rowBounds);

}

