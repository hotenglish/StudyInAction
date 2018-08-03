package com.elson.chapter9.handlers;

import com.elson.chapter9.enums.Sex;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SexEnumTypeHandler implements TypeHandler<Sex> {

    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, Sex sex, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, sex.getCode());
    }

    @Override
    public Sex getResult(ResultSet resultSet, String s) throws SQLException {
        int result = resultSet.getInt(s);
        return Sex.getEnumByCode(result);
    }

    @Override
    public Sex getResult(ResultSet resultSet, int i) throws SQLException {
        int result = resultSet.getInt(i);
        return Sex.getEnumByCode(result);
    }

    @Override
    public Sex getResult(CallableStatement callableStatement, int i) throws SQLException {
        int result = callableStatement.getInt(i);
        return Sex.getEnumByCode(result);
    }

}
