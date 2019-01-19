package com.manage.platform.dao.impl;

import com.manage.platform.dao.IMANAGE_ROLEDao;
import com.manage.platform.entity.MANAGE_ROLEEntity;
import net.sf.json.JSONObject;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MANAGE_ROLEDaoImpl extends DaoImplBase implements IMANAGE_ROLEDao {

    @Override
    public int insert(MANAGE_ROLEEntity entity) {
        try {
            String sql = "insert into MANAGE_ROLE(ICODE,FULLNAME)" +
                    " VALUES(:ICODE,:FULLNAME)";
            SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(entity);
            return this.namedjdbcTemplate.update(sql.toString(), namedParameters);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return 0;
    }

    @Override
    public int update(MANAGE_ROLEEntity entity) {
        StringBuffer sql = new StringBuffer();
        sql.append(" UPDATE MANAGE_ROLE SET ");
        sql.append(" FULLNAME =:FULLNAME ");
        sql.append(" WHERE ICODE=:ICODE");
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(entity);
        return this.namedjdbcTemplate.update(sql.toString(), namedParameters);
    }

    private String buildCondition(String condition) {
        StringBuffer sb = new StringBuffer();

        //=========================================================================
        // 按主键查询
        //=========================================================================
        if (null != condition && !condition.isEmpty()) {
            JSONObject obj = JSONObject.fromObject(condition);
            String ICODE = obj.containsKey("ICODE") ? obj.getString("ICODE") : "";
            if (null != ICODE && ICODE.length() > 0) {
                sb.append(" ICODE='" + obj.get("ICODE") + "' ");
                return sb.toString();
            }
        }

        //前台页面 摸人加载数据也是 默认触发查询按钮产生的，所以，查询条件肯定是有的了。
        if (null != condition && !condition.isEmpty()) {
            JSONObject obj = JSONObject.fromObject(condition);

            String FULLNAME = obj.containsKey("FULLNAME") ? obj.getString("FULLNAME") : "";
            if (null != FULLNAME && !FULLNAME.isEmpty()) {
                sb.append(" FULLNAME = '" + FULLNAME + "' ");
            }
        }

        return sb.toString();
    }

    @Override
    public List<Map<String, Object>> findByCondition(String condition, int start, int count) {
        condition = buildCondition(condition);
        StringBuffer sql = new StringBuffer();
        sql.append("                    select ");
        sql.append("               		MANAGE_ROLE.*, row_number() OVER(ORDER BY null) AS row_number ");
        sql.append("               		from MANAGE_ROLE ");
        if (null != condition && condition.length() > 0) {
            sql.append("                where " + condition);
        }
        sql = pageSql(sql, start, count);
        logger.info(sql.toString());
        List<Map<String, Object>> list = namedjdbcTemplate.getJdbcOperations().queryForList(sql.toString());
        return list;
    }

    @Override
    public int countByCondition(String condition) {
        condition = buildCondition(condition);
        StringBuffer sql = new StringBuffer();
        sql.append(" select count(1) from MANAGE_ROLE ");
        if (null != condition && condition.length() > 0) {
            sql.append(" where " + condition);
        }
        return namedjdbcTemplate.getJdbcOperations().queryForInt(sql.toString());
    }

    @Override
    public int delete(String uuid) {
        StringBuffer sql = new StringBuffer();
        sql.append("delete from MANAGE_ROLE ");
        sql.append(" where ICODE =:ICODE");

        Map paramMap = new HashMap();
        paramMap.put("ICODE", uuid);
        return namedjdbcTemplate.update(sql.toString(), paramMap);
    }

}
