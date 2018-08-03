package com.elson.chapter9.main;

import com.elson.chapter9.mapper.ColorMapper;
import com.elson.chapter9.pojo.ColorBean;
import com.elson.chapter9.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;

public class C9EnumMain {

    public static void main(String args[]) {
        SqlSession session = SqlSessionFactoryUtil.openSqlSession();
        try {
            ColorMapper colorMapper = session.getMapper(ColorMapper.class);
            ColorBean colorBean = colorMapper.getColor(1);
            System.err.println(colorBean);
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
