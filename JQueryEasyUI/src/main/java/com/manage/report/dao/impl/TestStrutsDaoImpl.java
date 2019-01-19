package com.manage.report.dao.impl;

import com.manage.platform.dao.impl.DaoImplBase;
import com.manage.report.dao.ITestStrutsDao;
import net.sf.json.JSONObject;

import java.util.List;
import java.util.Map;

public class TestStrutsDaoImpl extends DaoImplBase implements ITestStrutsDao {

    private String buildCondition(String condition) {
        StringBuffer sb = new StringBuffer();
        if (null != condition && !condition.isEmpty()) {
            JSONObject obj = JSONObject.fromObject(condition);
            String tbUsername = obj.containsKey("tbUsername") ? obj.getString("tbUsername") : "";
            if (null != tbUsername && !tbUsername.isEmpty()) {
                sb.append(" LOGINNAME like '%" + tbUsername + "%' ");
            }
        }
        return sb.toString();
    }

    @Override
    public List<Map<String, Object>> findByCondition(String condition) {
        condition = buildCondition(condition);
        StringBuffer sql = new StringBuffer();
        sql.append("                    select ");
        sql.append("               		MANAGE_USER.*, row_number() OVER(ORDER BY null) AS row_number ");
        sql.append("               		from MANAGE_USER ");
        if (null != condition && condition.length() > 0)
            sql.append("                where " + condition);
        logger.info(sql.toString());
        List<Map<String, Object>> list = namedjdbcTemplate.getJdbcOperations().queryForList(sql.toString());
        return list;
    }

}
