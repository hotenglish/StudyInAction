package com.elson.chapter4.insert.main;

import com.elson.chapter4.insert.dao.RoleDao;
import com.elson.chapter4.insert.pojo.Role;
import com.elson.chapter4.insert.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.logging.Logger;

public class C4Main {

    private static Logger logger = Logger.getLogger(C4Main.class.getName());

    public static void main(String[] args) {
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionFactoryUtil.openSqlSession();
            RoleDao roleDao = sqlSession.getMapper(RoleDao.class);
            Role param=new Role();
            param.setRoleName("me11");
            param.setNote("te22");
            System.out.println(roleDao.insertRole(param));
            System.out.println(param.getId());
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            sqlSession.rollback();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
        logger.info("执行成功!");
    }

}
