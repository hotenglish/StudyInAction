package com.manage.platform.dao.impl;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class DaoImplBase {

    protected JdbcTemplate jdbcTemplate;
    protected NamedParameterJdbcTemplate namedjdbcTemplate;
    protected static final Logger logger= LogManager.getLogger("interfaceLogger");

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public NamedParameterJdbcTemplate getNamedjdbcTemplate() {
        return namedjdbcTemplate;
    }

    public void setNamedjdbcTemplate(NamedParameterJdbcTemplate namedjdbcTemplate) {
        this.namedjdbcTemplate = namedjdbcTemplate;
    }


    public StringBuffer pageSql(StringBuffer sql_in,int start, int count) {
        StringBuffer sql = new StringBuffer();
        sql.append(" select * from (");
        sql.append("     select * from (");
        sql.append(sql_in);
        sql.append("                ) p ");
        sql.append("          where p.row_number >= " + start + ") q ");
        sql.append("  where rownum <= " + count + " ");
        return sql;
    }
}
