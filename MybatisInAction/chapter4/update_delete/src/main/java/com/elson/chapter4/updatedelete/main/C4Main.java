package com.elson.chapter4.updatedelete.main;

import com.elson.chapter4.updatedelete.dao.RoleDao;
import com.elson.chapter4.updatedelete.pojo.Role;
import com.elson.chapter4.updatedelete.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class C4Main {

    private static Logger logger = LogManager.getLogger(C4Main.class.getName());

    public static void main(String[] args) {
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionFactoryUtil.openSqlSession();
            RoleDao roleDao = sqlSession.getMapper(RoleDao.class);
            Role param=new Role();
            param.setId(2L);
            param.setRoleName("12222");
            param.setNote("3333");
            roleDao.updateRole(param);
            roleDao.selectAllData().forEach(x->System.out.println(x.toString()));
            System.out.println("-------------------------------------------------------------->");
            roleDao.delete(param);
            roleDao.selectAllData().forEach(x -> System.out.println(x.toString()));
            System.out.println("-------------------------------------------------------------->");
        }catch (Exception e){
            e.printStackTrace();
            System.err.println(e.getMessage());
        }finally {
            if(sqlSession!=null){
                sqlSession.close();
            }
        }
        logger.info("执行成功!");
    }
}
