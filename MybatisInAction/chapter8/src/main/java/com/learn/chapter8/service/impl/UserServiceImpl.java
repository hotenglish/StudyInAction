package com.learn.chapter8.service.impl;

import com.learn.chapter8.dao.UserDao;
import com.learn.chapter8.pojo.UserBean;
import com.learn.chapter8.service.UserService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public UserBean getUser(Integer id) {
        return userDao.getUser(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int insertUser(UserBean user) {
        return userDao.insertUser(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int deleteUser(Integer id) {
        return userDao.deleteUser(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int updateUser(UserBean user) {
        return userDao.updateUser(user);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<UserBean> findUsers(String userName, int start, int limit) {
        return userDao.findUsers(userName, new RowBounds(start, limit));
    }
}
