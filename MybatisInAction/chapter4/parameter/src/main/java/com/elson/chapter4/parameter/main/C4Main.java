package com.elson.chapter4.parameter.main;

import com.elson.chapter4.parameter.dao.RoleDao;
import com.elson.chapter4.parameter.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class C4Main {

    private static Logger logger = LogManager.getLogger(C4Main.class.getName());

    public static void main(String[] args) {
        queryByAnnotation();
        queryByMapParameter();
    }

    private static void queryByMapParameter() {
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionFactoryUtil.openSqlSession();
            RoleDao roleDao = sqlSession.getMapper(RoleDao.class);
            Map<String, String> paramsMap = new HashMap<>(2);
            paramsMap.put("roleName", "me");
            paramsMap.put("note", "te");
            roleDao.findRoleByMap(paramsMap).forEach(x -> System.out.println(x.toString()));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
        logger.info("执行成功!");
    }

    private static void queryByAnnotation() {
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionFactoryUtil.openSqlSession();
            RoleDao roleDao = sqlSession.getMapper(RoleDao.class);
            roleDao.findRoleByAnnotation("me", "te").forEach(x -> System.out.println(x.toString()));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
        logger.info("执行成功!");
    }
}
