package com.learn.chapter8.service.impl;

import com.learn.chapter8.dao.RoleDao;
import com.learn.chapter8.pojo.RoleBean;
import com.learn.chapter8.service.RoleService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int insertRole(RoleBean role) {
        return roleDao.insertRole(role);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int updateRole(RoleBean role) {
        return roleDao.updateRole(role);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int deleteRole(Integer id) {
        return roleDao.deleteRole(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public RoleBean getRole(Integer id) {
        return roleDao.getRole(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<RoleBean> findRoles(String roleName, int start, int limit) {
        return roleDao.findRoles(roleName, new RowBounds(start, limit));
    }
}
