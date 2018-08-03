package com.elson.chapter4.params.dao;

import com.elson.chapter4.params.pojo.Role;
import com.elson.chapter4.params.pojo.RoleParam;

import java.util.List;

public interface RoleDao {

    List<Role> findRoleByParams(RoleParam params);

}
