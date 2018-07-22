package com.elson.chapter3.databaseidprovider;

import org.apache.ibatis.mapping.DatabaseIdProvider;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

public class MydatabaseIdProvider implements DatabaseIdProvider {

    private Properties properties=null;

    @Override
    public void setProperties(Properties properties) {
        this.properties=properties;
    }

    @Override
    public String getDatabaseId(DataSource dataSource) throws SQLException {
        String dbName=dataSource.getConnection().getMetaData().getDatabaseProductName();
        String dbId=(String)this.properties.get(dbName);
        return dbId;
    }
}
