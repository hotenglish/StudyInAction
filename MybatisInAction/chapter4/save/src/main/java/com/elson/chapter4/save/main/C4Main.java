package com.elson.chapter4.save.main;

import com.elson.chapter4.save.dao.RoleDao;
import com.elson.chapter4.save.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * 4.7.2 :
 * 使用Map存储结果集
 * 4.7.3 :
 * 使用POJO存储结果集
 */
public class C4Main {

    private static Logger logger = LogManager.getLogger(C4Main.class.getName());

    public static void main(String[] args) {
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionFactoryUtil.openSqlSession();
            RoleDao roleDao = sqlSession.getMapper(RoleDao.class);
            System.out.println(roleDao.getRole(2L).toString());
            System.out.println("-------------------------------------------------------------->");
            Map map=(HashMap)roleDao.findRoleByNote("te");
            System.out.println(map);
            System.out.println("-------------------------------------------------------------->");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
        logger.info("执行成功!");
    }
}
