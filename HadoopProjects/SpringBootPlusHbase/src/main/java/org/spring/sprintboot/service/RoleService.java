package org.spring.sprintboot.service;

import org.apache.ibatis.session.RowBounds;
import org.spring.sprintboot.domain.Role;

import java.util.List;
import java.util.Map;

public interface RoleService {

    Role query(Long id);

    void deleteRole(Long id);

    void saveOrUpdate(Role role);

    void removeRoleFromUser(Map<String, Long> params);

    void addRoleForUser(Map<String, Long> params);

    List<Role> findRolesByName(String name, RowBounds rowBounds);

}
