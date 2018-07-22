package com.elson.chapter4.select.main;

import com.elson.chapter4.select.dao.UserDao;
import com.elson.chapter4.select.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

public class C4Main {

    private static Logger logger = Logger.getLogger(C4Main.class.getName());

    public static void main(String args[]) {
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionFactoryUtil.openSqlSession();
            UserDao userDao= sqlSession.getMapper(UserDao.class);
            System.out.println(userDao.countFirstName("z"));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
        logger.info("执行成功!");
    }

}
