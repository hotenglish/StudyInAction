package com.elson.chapter2.main;

import com.elson.chapter2.mapper.RoleMapperAnnotation;
import com.elson.chapter2.po.Role;
import com.elson.chapter2.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import java.io.IOException;

public class Chapter2MainAnnotation {

    private static Logger logger = Logger.getLogger(Chapter2MainAnnotation.class.getName());

    public static void main(String[] args) throws IOException {
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionFactoryUtil.openSqlSession();
            RoleMapperAnnotation roleMapperAnnotation = sqlSession.getMapper(RoleMapperAnnotation.class);
            Role role=roleMapperAnnotation.getRole(1L);
            logger.info(role);
            sqlSession.commit();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            sqlSession.rollback();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
        logger.info("执行成功!");
    }
}
