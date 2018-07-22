package com.elson.dao;

import org.apache.ibatis.annotations.Param;
import com.elson.pojo.User;
import java.util.List;

public interface UserDao {
    /**
     * 5.5 foreach
     */
    List<User> findUserBySex3(@Param("sexList") List<Integer> list);
}
