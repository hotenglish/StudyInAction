package com.elson.chapter4.cache.main;

import com.elson.chapter4.cache.dao.StudentDao;
import com.elson.chapter4.cache.pojo.Student;
import com.elson.chapter4.cache.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * 4.8.1 -- 缓存
 */
public class C4Main {

    private static Logger logger = LogManager.getLogger(C4Main.class);

    public static void main(String args[]){
        SqlSession sqlSession = null;
        SqlSession sqlSession2 = null;
        try {
            sqlSession = SqlSessionFactoryUtil.openSqlSession();
            StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
            Student student = studentDao.getStudent(1);
            logger.info("使用同一个sqlSession再执行一次");
            Student student1 = studentDao.getStudent(1);
            // 请注意,当我们使用二级缓存的时候,sqlSession调用了commit方法后才会生效.
            List<Student> sutdents1=studentDao.findAllStudent();
            logger.info(sutdents1);
            sqlSession.commit();
            logger.info("现在创建一个新的sqlSession再执行一次");
            sqlSession2 = SqlSessionFactoryUtil.openSqlSession();
            StudentDao studentDao1 = sqlSession2.getMapper(StudentDao.class);
            Student student2 = studentDao1.getStudent(1);
            // 请注意,当我们使用二级缓存的时候,sqlSession调用了commit方法后才会生效
            List<Student> sutdents2=studentDao1.findAllStudent();
            logger.info(sutdents2);
            sqlSession2.commit();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
            if (sqlSession2 != null) {
                sqlSession2.close();
            }
        }
        logger.info("执行成功!");
    }
}
