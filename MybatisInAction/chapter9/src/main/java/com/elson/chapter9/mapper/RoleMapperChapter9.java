package com.elson.chapter9.mapper;

import com.elson.chapter9.util.QueryParams;
import org.apache.ibatis.session.RowBounds;
import com.elson.chapter9.pojo.Role;
import java.util.List;

public interface RoleMapperChapter9 {

    Role getRole(Long id);

    int insertRole(Role role);

    List<Role> findRoleByUserId(Long userId);

    List<Role> findRolesByName(String roleName, RowBounds rowBounds);

    List<Role> findRolesByName(QueryParams queryParams);
}
