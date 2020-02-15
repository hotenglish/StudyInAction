package org.spring.sprintboot.service;

import org.spring.sprintboot.domain.User;

import java.util.Map;

public interface UserService {

    User query(Long id);

    void deleteUser(Long id);

    void saveOrUpdate(User user);

    void removeUserFromRole(Map<String, Long> map) throws Exception;

    void addRoleForUser(Map<String, Long> map) throws Exception;

}
