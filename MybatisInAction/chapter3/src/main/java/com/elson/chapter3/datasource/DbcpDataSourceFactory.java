package com.elson.chapter3.datasource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.apache.ibatis.datasource.DataSourceFactory;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;

public class DbcpDataSourceFactory extends BasicDataSource implements DataSourceFactory {

    private Properties props=null;

    @Override
    public void setProperties(Properties props) {
        this.props=props;
    }

    @Override
    public DataSource getDataSource() {
        DataSource dataSource = null;
        try{
            dataSource= BasicDataSourceFactory.createDataSource(props);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return dataSource;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return Logger.getLogger(DbcpDataSourceFactory.class.getName());
    }
}
