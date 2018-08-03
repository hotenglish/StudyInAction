package com.elson.chapter4.params.main;

import com.elson.chapter4.params.dao.RoleDao;
import com.elson.chapter4.params.pojo.RoleParam;
import com.elson.chapter4.params.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class C4Main {

    private static Logger logger = LogManager.getLogger(C4Main.class.getName());

    public static void main(String args[]) throws Exception {
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionFactoryUtil.openSqlSession();
            RoleDao roleDao = sqlSession.getMapper(RoleDao.class);
            RoleParam param = new RoleParam();
            param.setRoleName("me");
            param.setNote("test");
            roleDao.findRoleByParams(param).forEach(x->System.out.println(x.toString()));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
        logger.info("执行成功!");
    }
}
