package com.elson.chapter9.main;

import com.elson.chapter9.mapper.RoleMapperChapter9;
import com.elson.chapter9.pojo.Role;
import com.elson.chapter9.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;

public class C9BatchMain {
    public static void main(String args[]) throws Exception {
        wrongBatchQuery();
        //rightBatchQuery();
    }

    private static void wrongBatchQuery(){
        SqlSession session = SqlSessionFactoryUtil.openSqlSession();
        try {
            RoleMapperChapter9 roleMapper = session.getMapper(RoleMapperChapter9.class);
            Role role = new Role();
            role.setNote("role_no_xxx");
            role.setRoleName("role_name_xxx");
            role.setNote("role_note_xxx");
            roleMapper.insertRole(role);
            Role role2 = roleMapper.getRole(role.getId());
            System.err.println(role2.getRoleName());
            session.commit();
        } catch (Exception ex) {
            session.rollback();
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    private static void rightBatchQuery(){
        SqlSession session = SqlSessionFactoryUtil.openSqlSession();
        try {
            RoleMapperChapter9 roleMapper = session.getMapper(RoleMapperChapter9.class);
            Role role = new Role();
            role.setNote("role_no_xxx");
            role.setRoleName("role_name_xxx");
            role.setNote("role_note_xxx");
            roleMapper.insertRole(role);
            session.flushStatements();
            Role role2 = roleMapper.getRole(role.getId());
            System.err.println(role2.getRoleName());
            session.commit();
        } catch (Exception ex) {
            session.rollback();
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
