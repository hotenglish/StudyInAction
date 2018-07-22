package com.elson.chapter5.dao;

import com.elson.chapter5.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    /**
     * 5.5 foreach
     *
     * @param list
     * @return
     */
    List<User> findUserBySex(@Param("sexList") List<Integer> list);
}
