package com.elson.chapter9.handlers;

import com.elson.chapter9.enums.Color;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ColorEnumTypeHandler implements TypeHandler<Color> {

    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, Color color, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, color.getCode());
    }

    @Override
    public Color getResult(ResultSet resultSet, String s) throws SQLException {
        int result = resultSet.getInt(s);
        return Color.getEnumByCode(result);
    }

    @Override
    public Color getResult(ResultSet resultSet, int i) throws SQLException {
        int reult = resultSet.getInt(i);
        return Color.getEnumByCode(reult);
    }

    @Override
    public Color getResult(CallableStatement callableStatement, int i) throws SQLException {
        int result = callableStatement.getInt(i);
        return Color.getEnumByCode(result);
    }

}
