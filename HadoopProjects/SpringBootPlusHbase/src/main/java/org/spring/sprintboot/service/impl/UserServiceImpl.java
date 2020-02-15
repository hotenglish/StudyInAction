package org.spring.sprintboot.service.impl;

import org.spring.sprintboot.dao.UserDao;
import org.spring.sprintboot.domain.User;
import org.spring.sprintboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User query(Long id) {
        return userDao.getUser(id);
    }

    @Override
    public void addRoleForUser(Map<String, Long> params) throws Exception {
        User user = userDao.getUser(params.get("userId"));
        if (user == null) {
            throw new Exception("No user!");
        }
        userDao.addRoleForUser(params);
    }

    @Override
    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }

    @Override
    public void saveOrUpdate(User user) {
        userDao.saveOrUpdate(user);
    }

    public void removeUserFromRole(Map<String, Long> params) throws Exception {
        User user = userDao.getUser(params.get("userId"));
        if (user == null) {
            throw new Exception("No user!");
        }
        userDao.removeUserFromRole(params);
    }

}
