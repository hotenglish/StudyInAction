package com.elson.chapter2.main;

import com.elson.chapter2.mapper.RoleMapper;
import com.elson.chapter2.po.Role;
import com.elson.chapter2.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import java.io.IOException;
import java.util.logging.Logger;

public class Chapter2Main {

    private static Logger logger = Logger.getLogger(Chapter2Main.class.getName());

    public static void main(String[] args) throws IOException {
        SqlSession sqlSession = null;
        Role role=null;
        try {
            sqlSession = SqlSessionFactoryUtil.openSqlSession();
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            role=new Role();
            role.setRoleName("testName");
            role.setNote("testNote");
            int insertedRoleRows=roleMapper.insertRole(role);
            System.out.println(insertedRoleRows);
            System.out.println(role);
            role=roleMapper.getRole(role.getId());
            System.out.println(role);
            int deletedRoleRows=roleMapper.deleteRole(role.getId());
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
}
