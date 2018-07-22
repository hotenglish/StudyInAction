package com.elson.main;

import com.elson.dao.RoleDao;
import com.elson.dao.UserDao;
import com.elson.pojo.Role;
import com.elson.pojo.User;
import com.elson.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class C7Main {
    private static final Logger log = Logger.getLogger(C7Main.class.getName());

    public static void main(String args[]) {
        SqlSession sqlSession = SqlSessionFactoryUtil.openSqlSession();
        RoleDao roleDao = sqlSession.getMapper(RoleDao.class);
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<Integer> sexList=new ArrayList<>();
        sexList.add(1);
        sexList.add(0);
        List<User> users=userDao.findUserBySex3(sexList);
        users.forEach(System.out::println);
        Role role = new Role();
        role.setNote("wong");
        role.setRoleName("elson");
        roleDao.insertRole(role);

        log.info("执行 C7Main 成功!");
    }

}
