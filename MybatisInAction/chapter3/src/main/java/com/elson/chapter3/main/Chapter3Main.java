package com.elson.chapter3.main;

import com.elson.chapter3.po.EnumOrdinalTypeHandlerSex;
import com.elson.chapter3.po.User;
import mapper.RoleMapper;
import com.elson.chapter3.po.Role;
import com.elson.chapter3.util.SqlSessionFactoryUtil;
import mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Date;

import static com.elson.chapter3.po.EnumTypeHandlerSex.MALE;

public class Chapter3Main {

    private static Logger logger = Logger.getLogger(Chapter3Main.class.getName());

    public static void main(String[] args) throws IOException {
        //execute32();
        //execute34();
        testEnumOrdinalTypeHandler();
        //testEnumTypeHandler();
        //execute38_1();
    }

    public static void execute32() {
        SqlSession sqlSession = null;
        Role role = null;
        try {
            sqlSession = SqlSessionFactoryUtil.openSqlSession();
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            role = new Role();
            role.setRoleName("Elson");
            role.setNote("ElsonNote");
            int insertedRoleRows = roleMapper.insertRole(role);
            System.out.println(insertedRoleRows);
            System.out.println(role);
            role = roleMapper.getRole(role.getId());
            System.out.println(role);
            int deletedRoleRows = roleMapper.deleteRole(role.getId());
            System.out.println(deletedRoleRows);
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

    public static void execute34() {
        SqlSession sqlSession = null;
        Role role = null;
        try {
            sqlSession = SqlSessionFactoryUtil.openSqlSession();
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            role = roleMapper.getRole(1L);
            System.out.println(role);
            role = roleMapper.findRole("test");
            System.out.println(role);
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

    public static void testEnumOrdinalTypeHandler() {
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionFactoryUtil.openSqlSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = new User();
            user.setUserName("zhangsan");
            user.setCnName("张三");
            user.setMobile("18888888");
            user.setSex(EnumOrdinalTypeHandlerSex.MALE);
            user.setBirthday(new Date());
            user.setEmail("zhangsan@163.com");
            user.setNote("test EnumOrdinalTypeHandler!!");
            userMapper.insertUser(user);
            User user2 = userMapper.getUser(user.getId());
            System.out.println("Sex == " + user2.getSex());
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

/*    public static void testEnumTypeHandler(){
        SqlSession sqlSession=null;
        try{
            sqlSession = SqlSessionFactoryUtil.openSqlSession();
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            User user=new User();
            user.setUserName("lishi");
            user.setCnName("李四");
            user.setMobile("18888888");
            user.setSex(MALE);
            user.setBirthday(new Date());
            user.setEmail("zhangsan@163.com");
            user.setNote("test EnumOrdinalTypeHandler!!");
            userMapper.insertUser(user);
            User user2=userMapper.getUser(1L);
            System.out.println("Sex == "+user2.getSex());
            sqlSession.commit();
        }catch (Exception ex){
            System.err.println(ex.getMessage());
            sqlSession.rollback();
        }finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
        logger.info("执行成功!");
    }*/

    public static void execute38_1() {
        SqlSession sqlSession=SqlSessionFactoryUtil.openSqlSession();
        String databaseId = sqlSession.getConfiguration().getDatabaseId();
        System.out.println(databaseId);
        sqlSession.close();
        logger.info("执行成功!");
    }

}
