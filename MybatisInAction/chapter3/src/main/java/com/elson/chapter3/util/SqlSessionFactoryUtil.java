package com.elson.chapter3.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.InflaterInputStream;

public class SqlSessionFactoryUtil {
    //SqlSessionFactory 对象
    private static SqlSessionFactory sqlSessionFactory = null;
    //类线程锁
    private static final Class CLASS_LOCK = SqlSessionFactoryUtil.class;

    /**
     * 私有化构造参数
     */
    private SqlSessionFactoryUtil() {
    }

    /**
     * 构建 SqlSessionFactory
     */
    public static SqlSessionFactory initSqlSessionFactory() {
        InputStream cfgStream = null;
        Reader cfgReader = null;
        InputStream proStream = null;
        Reader proReader = null;
        Properties properties = null;

        try {
            // 读入配置文件流
            cfgStream = Resources.getResourceAsStream("mybatis-config.xml");
            cfgReader = new InputStreamReader(cfgStream);
            // 读入属性文件
            proStream = Resources.getResourceAsStream("jdbc.properties");
            proReader = new InputStreamReader(proStream);
            properties = new Properties();
            properties.load(proReader);
            // 解密为明文
            properties.setProperty("username", decode(properties.getProperty("username")));
            properties.setProperty("password", decode(properties.getProperty("password")));
        } catch (Exception ex) {
            Logger.getLogger(SqlSessionFactoryUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        synchronized (CLASS_LOCK) {
            if (sqlSessionFactory == null) {
                // 使用属性来创建 SqlSessoinFactory
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(cfgReader, properties);
            }
        }
        return sqlSessionFactory;
    }

    /**
     * 打开 SqlSession
     */
    public static SqlSession openSqlSession() {
        if (sqlSessionFactory == null) {
            initSqlSessionFactory();
        }
        return sqlSessionFactory.openSession();
    }

    //模拟解密
    public static String decode(String input) throws Exception{
        if (input == null) {
            throw new Exception("没此属性值！");
        }
        return input;
    }

}
