package org.spring.sprintboot.dao;

import org.apache.ibatis.annotations.Mapper;
import org.spring.sprintboot.domain.User;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserDao {

    User getUser(Long id);

    List<User> findUserByRoleId(Long roleId);

    void deleteUser(Long id);

    int saveOrUpdate(User user);

    void removeUserFromRole(Map map);

    void addRoleForUser(Map map);

}

