package com.elson.chapter9.mapper;

import com.elson.chapter9.pojo.User;
import java.util.List;

public interface UserMapperChapter9 {

    User getUser(Long id);

    List<User> findUserByRoleId(Long userId);

}
