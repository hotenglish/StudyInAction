package com.elson.chapter3.handlers;

import com.elson.chapter3.po.EnumOrdinalTypeHandlerSex;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.apache.log4j.Logger;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SexEnumTypeHandler implements TypeHandler<EnumOrdinalTypeHandlerSex> {

    private static Logger logger = Logger.getLogger(SexEnumTypeHandler.class.getName());

    @Override
    public void setParameter(PreparedStatement ps, int i, EnumOrdinalTypeHandlerSex enumOrdinalTypeHandlerSex, JdbcType jdbcType) throws SQLException {
        logger.info("Use my SexEnumTypeHandler");
        ps.setInt(i, enumOrdinalTypeHandlerSex.getId());
    }

    @Override
    public EnumOrdinalTypeHandlerSex getResult(ResultSet rs, String name) throws SQLException {
        logger.info("Use my SexEnumTypeHandler,ResultSet 列名获取字符串");
        int id = rs.getInt(name);
        return EnumOrdinalTypeHandlerSex.getSex(id);
    }

    @Override
    public EnumOrdinalTypeHandlerSex getResult(ResultSet rs, int i) throws SQLException {
        logger.info("Use my SexEnumTypeHandler,ResultSet 下标获取字符串");
        int id = rs.getInt(i);
        return EnumOrdinalTypeHandlerSex.getSex(id);
    }

    @Override
    public EnumOrdinalTypeHandlerSex getResult(CallableStatement cs, int i) throws SQLException {
        logger.info("Use my SexEnumTypeHandler,CallableStatement 下标获取字符串");
        int id = cs.getInt(i);
        return EnumOrdinalTypeHandlerSex.getSex(id);
    }
}
