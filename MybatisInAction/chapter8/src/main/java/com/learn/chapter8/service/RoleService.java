package com.learn.chapter8.service;

import com.learn.chapter8.pojo.RoleBean;

import java.util.List;

public interface RoleService {

    public int insertRole(RoleBean role);

    public int updateRole(RoleBean role);

    public int deleteRole(Integer id);

    public RoleBean getRole(Integer id);

    public List<RoleBean> findRoles(String rolename, int start, int limit);

}
