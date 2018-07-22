package com.elson.chapter3.handlers;

import com.elson.chapter3.po.EnumOrdinalTypeHandlerSex;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SexEnumTypeHandler implements TypeHandler<EnumOrdinalTypeHandlerSex> {

    @Override
    public void setParameter(PreparedStatement ps, int i, EnumOrdinalTypeHandlerSex enumOrdinalTypeHandlerSex, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, enumOrdinalTypeHandlerSex.getId());
    }

    @Override
    public EnumOrdinalTypeHandlerSex getResult(ResultSet rs, String name) throws SQLException {
        int id = rs.getInt(name);
        return EnumOrdinalTypeHandlerSex.getSex(id);
    }

    @Override
    public EnumOrdinalTypeHandlerSex getResult(ResultSet rs, int i) throws SQLException {
        int id = rs.getInt(i);
        return EnumOrdinalTypeHandlerSex.getSex(id);
    }

    @Override
    public EnumOrdinalTypeHandlerSex getResult(CallableStatement cs, int i) throws SQLException {
        int id = cs.getInt(i);
        return EnumOrdinalTypeHandlerSex.getSex(id);
    }
}
