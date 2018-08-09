package com.elson.chapter5.main;

import com.elson.chapter5.dao.RoleDao;
import com.elson.chapter5.dao.UserDao;
import com.elson.chapter5.pojo.Role;
import com.elson.chapter5.pojo.User;
import com.elson.chapter5.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.List;

/**
 * 第五章所有例子.
 * 5.2 if元素
 * 5.3 choose,when,otherwise元素
 * 5.4 trim,where,set元素
 * 5.5 foreach元素
 * 5.6 test的属性
 * 5.7 bind元素
 */
public class C5Main {

    private static final Logger log = LogManager.getLogger(C5Main.class);

    public static void main(String args[]) {
        SqlSession sqlSession = SqlSessionFactoryUtil.openSqlSession();
        RoleDao roleDao = sqlSession.getMapper(RoleDao.class);
        //execute52(roleDao);

        //execute53(roleDao);

        //execute54(roleDao, sqlSession);  // 代码文件在UserDao.xml

        //execute55(sqlSession); // 代码文件在UserDao.xml

        //execute56(roleDao);

        execute57(roleDao);

        log.info("执行成功!");
    }

    private static void execute52(RoleDao roleDao) {
        System.out.println("---------5.2----------->");
        roleDao.findRolesIf("test").forEach(x -> System.out.println(x.toString()));
        System.out.println("---------5.2end----------->");
    }

    private static void execute53(RoleDao roleDao) {
        System.out.println("---------5.3----------->");
        roleDao.findChooseWhenOtherWise("1", "test").forEach(x -> System.out.println(x.toString()));
        roleDao.findChooseWhenOtherWise("", "test").forEach(x -> System.out.println(x.toString()));
        System.out.println("---------5.3end----------->");
    }

    private static void execute54(RoleDao roleDao, SqlSession sqlSession) {
        System.out.println(roleDao.findRolesWhere("test"));
        System.out.println(roleDao.findRoleTrim("test"));
        Role role = new Role();
        role.setId(3L);
        role.setRoleName("update post");
        role.setNote("update note");
        System.out.println(roleDao.updateRole(role));
        sqlSession.commit();
    }

    private static void execute55(SqlSession sqlSession) {
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<User> userList = userDao.findUserBySex(Arrays.asList(0, 1, 2));
        userList.forEach(x -> System.out.println(x.toString()));
    }

    private static void execute56(RoleDao roleDao) {
        System.out.println(roleDao.getRoleTest("N"));
    }

    private static void execute57(RoleDao roleDao) {
        System.out.println(roleDao.findRoleMulBind("test", "4"));
    }

}
