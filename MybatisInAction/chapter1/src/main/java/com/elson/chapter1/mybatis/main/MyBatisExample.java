package com.elson.chapter1.mybatis.main;

import com.elson.chapter1.mybatis.mapper.RoleMapper;
import com.elson.chapter1.mybatis.po.Role;
import com.elson.chapter1.mybatis.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

public class MyBatisExample {

    public static void main(String[] args) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            Role role = roleMapper.getRole(2L);
            System.err.println("role_name=>" + role.getRoleName());
        } finally {
            sqlSession.close();
        }
    }
}
