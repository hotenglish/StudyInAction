package com.elson.chapter9.main;

import com.elson.chapter9.mapper.RoleMapperChapter9;
import com.elson.chapter9.mapper.UserMapperChapter9;
import com.elson.chapter9.pojo.Role;
import com.elson.chapter9.pojo.User;
import com.elson.chapter9.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import java.util.List;

public class C9MultipleRelationMain {

    public static void main(String args[]) throws Exception {
        SqlSession session = SqlSessionFactoryUtil.openSqlSession();
        RoleMapperChapter9 roleMapper = session.getMapper(RoleMapperChapter9.class);
        //取出角色，此时并不发送 SQL 取回用户，因为设置了延迟加载
        Role role = roleMapper.getRole(2L);
        // 访问用户，此时才会发送SQL,取回对应的用户信息
        List<User> userList=role.getUserList();
        userList.forEach(System.out::println);
        UserMapperChapter9 userMapper= session.getMapper(UserMapperChapter9.class);
        //取出角色，因延迟加载，所以不会发送SQL取回角色信息
        User user=userMapper.getUser(1L);
        System.out.println(user);
    }

}
