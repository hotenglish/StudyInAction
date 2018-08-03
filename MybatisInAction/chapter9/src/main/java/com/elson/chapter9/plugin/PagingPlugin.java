package com.elson.chapter9.plugin;

import com.elson.chapter9.util.PageParams;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.Configuration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

@Intercepts(@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class}))
public class PagingPlugin implements Interceptor {

    private Integer defaultPage;
    private Integer defaultPageSize;
    private Boolean defaultUseFlag;
    private Boolean defaultCheckFlag;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = getUnProxyObject(invocation);
        MetaObject metaStatementHandler = SystemMetaObject.forObject(statementHandler);
        String sql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");
        //不是select 语句
        if (!checkSelect(sql)) {
            return invocation.proceed();
        }
        BoundSql boundSql = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");
        Object parameterObject = boundSql.getParameterObject();
        PageParams pageParams = getPageParams(parameterObject);
        if (pageParams == null) { //没有分页参数，不启用插件
            return invocation.proceed();
        }
        //获取分页参数，获取不到时候使用默认值
        Integer pageNum = pageParams.getPage() == null ? this.defaultPage : pageParams.getPage();
        Integer pagesize = pageParams.getPageSize() == null ? this.defaultPageSize : pageParams.getPageSize();
        Boolean useFlag = pageParams.getUserFlag() == null ? this.defaultUseFlag : pageParams.getUserFlag();
        Boolean checkFlag = pageParams.getCheckFlag() == null ? this.defaultCheckFlag : pageParams.getCheckFlag();
        if (!useFlag) {  //不使用分页插件
            return invocation.proceed();
        }
        int total = getTotal(invocation, metaStatementHandler, boundSql);
        // 回填总数到分页参数里
        setTotalToPageParams(pageParams, total, pagesize);
        // 检查当前页码的有效性
        checkPage(checkFlag, pageNum, pageParams.getTotalPage());
        // 修改SQL
        return changeSQL(invocation, metaStatementHandler, boundSql, pageNum, pagesize);
    }

    /**
     * 分解分页参数，这里支持使用Map和@Param注解传递参数，或者POJO继承PageParams，这三种方式都是允许的
     *
     * @param parameterObject --sql允许参数
     * @return 分页参数
     */
    private PageParams getPageParams(Object parameterObject) {
        if (parameterObject == null) {
            return null;
        }
        PageParams pageParams = null;
        // 支持Map参数和Mybatis的@Param注解参数
        if (parameterObject instanceof Map) {
            Map<String, Object> paramMap = (Map<String, Object>) parameterObject;
            Set<String> keySet = paramMap.keySet();
            Iterator<String> iterator = keySet.iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                Object value = paramMap.get(key);
                if (value instanceof PageParams) {
                    return (PageParams) value;
                }
            }
        } else if (parameterObject instanceof PageParams) {  //继承方式
            pageParams = (PageParams) parameterObject;
        }
        return pageParams;
    }

    private void setTotalToPageParams(PageParams pageParams, int total, int pageSize) {
        pageParams.setTotal(total);
        // 计算总页数
        int totalPage = total % pageSize == 0 ? total / pageSize : total / pageSize + 1;
        pageParams.setTotalPage(totalPage);
    }

    /**
     * 从代理对象中分离出真实对象
     *
     * @param ivt --Invocation
     * @return 非代理StatementHandler 对象
     */
    private StatementHandler getUnProxyObject(Invocation ivt) {
        StatementHandler statementHandler = (StatementHandler) ivt.getTarget();
        MetaObject metaStatementHandler = SystemMetaObject.forObject(statementHandler);
        // 分离代理对象链 由于目标类可能被多个拦截器拦截，从而形成多次代理，通过循环可
        // 以分离出最原始的目标类
        Object object = null;
        while (metaStatementHandler.hasGetter("h")) {
            object = metaStatementHandler.getValue("h");
        }
        if (object == null) {
            return statementHandler;
        }
        return (StatementHandler) object;
    }

    @Override
    public Object plugin(Object statementHandler) {
        return Plugin.wrap(statementHandler, this);
    }

    @Override
    public void setProperties(Properties props) {
        String strDefaultPage = props.getProperty("default.page", "1");
        String strDefaultPageSize = props.getProperty("default.pageSize", "50");
        String strDefaultUseFlag = props.getProperty("default.userFlag", "false");
        String strDefaultCheckFlag = props.getProperty("default.checkFlag", "false");
        this.defaultPage = Integer.parseInt(strDefaultPage);
        this.defaultPageSize = Integer.parseInt(strDefaultPageSize);
        this.defaultCheckFlag = Boolean.parseBoolean(strDefaultUseFlag);
        this.defaultUseFlag = Boolean.parseBoolean(strDefaultCheckFlag);
    }

    /**
     * 判断是否select语句
     *
     * @param sql
     * @return
     */
    private boolean checkSelect(String sql) {
        String trimSql = sql.trim();
        int idx = trimSql.toLowerCase().indexOf("select");
        return idx == 0;
    }

    /**
     * 获取总数
     *
     * @param ivt
     * @param metaStatementHandler
     * @param boundSql             sql
     * @return sql 查询总数
     * @throws Throwable 异常
     */
    private int getTotal(Invocation ivt, MetaObject metaStatementHandler, BoundSql boundSql) throws Throwable {
        // 获取当前的 mappedStatement
        MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");
        // 配置对象
        Configuration cfg = mappedStatement.getConfiguration();
        // 当前需要执行的SQL
        String sql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");
        // 改写为统计总数的SQL,这里是MySQL数据库，如果是其他的数据库，需要按数据库的SQL规范改写
        String countSql = "select count(*) as total from (" + sql + ") $_paging";
        // 获取拦截方法参数，我们知道是Connection 对象
        Connection connection = (Connection) ivt.getArgs()[0];
        PreparedStatement ps = null;
        int total = 0;
        try {
            // 预编译统计总数SQL
            ps = connection.prepareStatement(countSql);
            // 构建统计总数BoundSql
            BoundSql countBoundSql = new BoundSql(cfg, countSql, boundSql.getParameterMappings(), boundSql.getParameterObject());
            // 构建Mybatis的 ParameterHandler 用来设置总数SQL的参数
            ParameterHandler handler = new DefaultParameterHandler(mappedStatement, boundSql.getParameterObject(), countBoundSql);
            //设置总数SQL参数
            handler.setParameters(ps);
            //执行查询
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                total = rs.getInt("total");
            }
        } finally {
            // 这里不能关闭Connection，否则后续的SQL就没法继续了
            if (ps != null) {
                ps.close();
            }
        }
        System.err.println("总条数：" + total);
        return total;
    }


    /**
     * 检查当前页码的有效性
     *
     * @param checkFlag
     * @param pageNum
     * @param pageTotal
     * @throws Throwable
     */
    private void checkPage(boolean checkFlag, Integer pageNum, Integer pageTotal) throws Throwable {
        if (checkFlag) {
            //查看页码 page 是否合法
            if (pageNum > pageTotal) {
                throw new Exception("查询失败，查询页码[" + pageNum + "]大于总页数[" + pageTotal + "]!");
            }
        }
    }

    /**
     * 修改当前查询的SQL
     *
     * @param invocation
     * @param metaStatementHandler
     * @param boundSql
     * @param page
     * @param pageSize
     * @return
     * @throws Exception
     */
    private Object changeSQL(Invocation invocation, MetaObject metaStatementHandler,
                             BoundSql boundSql, int page, int pageSize) throws Exception {
        //获取当前需要执行的SQL
        String sql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");
        //修改SQL,这里使用的是MySQL,如果是其他数据库则需要修改
        String newSql = "select * from (" + sql + ") $paging_table limit ?,?";
        //修改当前需要执行的SQL
        metaStatementHandler.setValue("delegate.boundSql.sql", newSql);
        //相当于条用StatementHandler的prepare方法，预编译了当前SQL并设置原有的参数，但是少了两个分页参数，
        //它返回的是一个PreparedStatement对象
        PreparedStatement ps = (PreparedStatement) invocation.proceed();
        //计算SQL总参数个数
        int count = ps.getParameterMetaData().getParameterCount();
        //设置两个分页参数
        ps.setInt(count - 1, (page - 1) * pageSize);
        ps.setInt(count, pageSize);
        return ps;
    }

}
