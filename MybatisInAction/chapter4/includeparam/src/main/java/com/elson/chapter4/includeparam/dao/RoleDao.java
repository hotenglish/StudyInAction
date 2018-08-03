package com.elson.chapter4.includeparam.dao;

import com.elson.chapter4.includeparam.pojo.Role;
import java.util.List;
import java.util.Map;

public interface RoleDao {

    Role getRoleCustom(Integer id);

    List<Role> findRoles(Map<String, String> params);

    Role getRole(Integer id);

}
