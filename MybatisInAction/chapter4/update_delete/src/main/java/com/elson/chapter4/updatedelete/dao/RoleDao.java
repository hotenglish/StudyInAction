package com.elson.chapter4.updatedelete.dao;

import com.elson.chapter4.updatedelete.pojo.Role;
import java.util.List;

public interface RoleDao {
    int updateRole(Role role);
    int delete(Role role);
    List<Role> selectAllData();
}
