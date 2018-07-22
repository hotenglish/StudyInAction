package com.elson.dao;

import com.elson.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    /**
     * 5.5 foreach
     */
    List<User> findUserBySex(@Param("sexList") List<Integer> list);
}
