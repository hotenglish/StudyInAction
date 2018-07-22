package com.elson.main;

import com.elson.dao.RoleDao;
import com.elson.pojo.Role;
import com.elson.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.logging.Logger;

public class C7Main2 {
    private static final Logger log = Logger.getLogger(C7Main2.class.getName());

    public static void main(String args[]) {
        SqlSession sqlSession = SqlSessionFactoryUtil.openSqlSession();
        RoleDao roleDao = sqlSession.getMapper(RoleDao.class);

        Role role = new Role();
        role.setRoleName("lym");
        role.setNote("wj");
        roleDao.insertRole(role);

        log.info("执行成功!");
    }

}
