package com.learn.chapter8.service;

import com.learn.chapter8.pojo.UserBean;

import java.util.List;

public interface UserService {

    public UserBean getUser(Integer id);

    public int insertUser(UserBean user);

    public int deleteUser(Integer id);

    public int updateUser(UserBean user);

    public List<UserBean> findUsers(String userName, int start, int limit);

}
