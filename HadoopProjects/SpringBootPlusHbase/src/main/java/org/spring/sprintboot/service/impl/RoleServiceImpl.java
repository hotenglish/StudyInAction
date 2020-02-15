package org.spring.sprintboot.service.impl;

import org.apache.ibatis.session.RowBounds;
import org.spring.sprintboot.dao.RoleDao;
import org.spring.sprintboot.domain.Role;
import org.spring.sprintboot.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public Role query(Long id) {
        return roleDao.getRole(id);
    }

    @Override
    public void deleteRole(Long id) {
        roleDao.deleteRole(id);
    }

    @Override
    public void saveOrUpdate(Role role) {
        roleDao.saveOrUpdate(role);
    }

    @Override
    public void removeRoleFromUser(Map<String, Long> params) {
        roleDao.removeRoleFromUser(params);
    }

    @Override
    public void addRoleForUser(Map<String, Long> params) {
        roleDao.addRoleForUser(params);
    }

    public List<Role> findRolesByName(String name, RowBounds rowBounds) {
        return roleDao.findRolesByName(name, new RowBounds(0, 5));
    }

}
