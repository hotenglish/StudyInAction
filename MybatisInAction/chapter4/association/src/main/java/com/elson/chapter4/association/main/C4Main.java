package com.elson.chapter4.association.main;

import com.elson.chapter4.association.dao.StudentDao;
import com.elson.chapter4.association.pojo.Lecture;
import com.elson.chapter4.association.pojo.Student;
import com.elson.chapter4.association.pojo.StudentLecture;
import com.elson.chapter4.association.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.logging.Logger;

public class C4Main {

    private static Logger logger = Logger.getLogger(C4Main.class.getName());

    public static void main(String args[]) {
        //oneToOneQuery();
        //oneToManyQuery();
        lazyLoadingQuery();
    }


    private static void lazyLoadingQuery() {
        SqlSession session = null;
        try {
            session = SqlSessionFactoryUtil.openSqlSession();
            StudentDao studentDao = session.getMapper(StudentDao.class);
            Student student = studentDao.getStudent(1);
            System.out.println("---------------eager-------------------");
            System.out.println(student.getStudentLectureList());
            System.out.println("---------------lazy-------------------");
            System.out.println(student.getStudentSelfCard());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        logger.info("执行成功!");
    }

    /**
     * 4.7.4.3 -- discriminator鉴别器级联.
     * ------------------------------------
     * <p>
     * <p>
     * MaleStudent未显示列表数据.
     * FemaleStudent未显示列表数据.
     * <p>
     */
    private static void oneToOneQuery() {
        SqlSession session = null;
        try {
            session = SqlSessionFactoryUtil.openSqlSession();
            StudentDao studentDao = session.getMapper(StudentDao.class);
            Student student = studentDao.getStudent(2);
            System.out.println(student);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        logger.info("执行成功!");
    }

    /**
     * 4.7.4.2 -- 一对多级联.
     */
    private static void oneToManyQuery() {
        SqlSession session = null;
        try {
            session = SqlSessionFactoryUtil.openSqlSession();
            StudentDao studentDao = session.getMapper(StudentDao.class);
            Student student = studentDao.getStudent(1);
            logger.info(student.getStudentSelfCard().getNative_());
            StudentLecture studentLecture = student.getStudentLectureList().get(0);
            Lecture lecture = studentLecture.getLecture();
            logger.info(student.getCnName() + "\t" + lecture.getLectureName() + "\t" + studentLecture.getGrade());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        logger.info("执行成功!");
    }
}
