package com.manage.data.action;

import com.manage.data.bean.Custom;
import com.manage.data.dao.CustomMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;

public class TestCustomMapper {

    private SqlSession session = null;

    public void setUp() throws Exception {
        String resource = "config.xml";
        Reader reader = Resources.getResourceAsReader(resource);
        SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader);
        session = sqlMapper.openSession();
    }

    public void testSelectCustom() {
        CustomMapper mapper = session.getMapper(CustomMapper.class);
        Custom custom = mapper.getCustomById(1);
        System.out.println(custom.toString());
    }

    public static void main(String args[]) throws Exception {
        TestCustomMapper test = new TestCustomMapper();
        test.setUp();
        test.testSelectCustom();
    }

}
