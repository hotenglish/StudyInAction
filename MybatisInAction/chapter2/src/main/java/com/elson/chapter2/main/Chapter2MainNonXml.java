package com.elson.chapter2.main;

import com.elson.chapter2.mapper.RoleMapper;
import com.elson.chapter2.po.Role;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import java.io.IOException;
import java.util.logging.Logger;

public class Chapter2MainNonXml {

    private static Logger logger = Logger.getLogger(Chapter2MainNonXml.class.getName());

    public static void main(String[] args) throws IOException {
        SqlSession sqlSession = null;
        try {
            sqlSession = createSqlSessionFactory().openSession();
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            Role role = roleMapper.getRole(2L);
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

    public static SqlSessionFactory createSqlSessionFactory() {
        //构建数据库连接池
        PooledDataSource dataSource = new PooledDataSource();
        dataSource.setDriver("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/mybatis?useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("");
        //构建数据库事务方式
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        //创建了数据库运行环境
        Environment environment = new Environment("development", transactionFactory, dataSource);
        //构建Configuration对象
        Configuration configuration = new Configuration(environment);
        //注册一个Mybatis上下文别名
        configuration.getTypeAliasRegistry().registerAlias("role", Role.class);
        //加入一个映射器
        configuration.addMapper(RoleMapper.class);
        //使用SqlSessionFactoryBuilder构建SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        return sqlSessionFactory;
    }

}
