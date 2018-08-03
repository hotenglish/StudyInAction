package com.elson.chapter4.automapping.main;

import com.elson.chapter4.automapping.dao.RoleDao;
import com.elson.chapter4.automapping.pojo.Role;
import com.elson.chapter4.automapping.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.logging.Logger;

public class C4Main {

    private static Logger logger= Logger.getLogger(C4Main.class.getName());

    public static void main(String args[]){
        SqlSession session=null;
        try{
            session= SqlSessionFactoryUtil.openSqlSession();
            RoleDao roleDao=session.getMapper(RoleDao.class);
            Role role=roleDao.getRole(2L);
            System.out.println(role);
            //roleMapper.deleteRole(1L);
            //sqlSession.commit();
        }catch (Exception e){
            System.err.println(e.getMessage());
            //sqlSession.rollback();
        }finally {
            if(session!=null){
                session.close();
            }
        }
        logger.info("执行成功!");
    }
}
