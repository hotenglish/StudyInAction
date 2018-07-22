package com.elson.chapter4.save.dao;

import com.elson.chapter4.save.pojo.Role;
import java.util.Map;

public interface RoleDao {

    Role getRole(Long id);

    Map<Object, Object> findRoleByNote(String id);

}
