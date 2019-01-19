package com.manage.platform.dao.impl;

import com.manage.platform.dao.IMANAGE_USERDao;
import com.manage.platform.entity.MANAGE_AREAEntity;
import com.manage.platform.entity.MANAGE_USEREntity;
import org.apache.struts2.ServletActionContext;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MANAGE_USERDaoImpl extends DaoImplBase implements IMANAGE_USERDao {

    @Override
    public int insert(MANAGE_USEREntity entity) {
        try {
            String sql = "insert into MANAGE_USER(ICODE,NO,NAME,PHONE,EMAIL,STOPFLAG,LOGINNAME,PASSWORD,AREAICODE)" +
                    " VALUES(:ICODE,:NO,:NAME,:PHONE,:EMAIL,:STOPFLAG,:LOGINNAME,:PASSWORD,:AREAICODE)";
            SqlParameterSource namedParameter = new BeanPropertySqlParameterSource(entity);
            return this.namedjdbcTemplate.update(sql, namedParameter);
        } catch (Exception e) {
            e.getMessage();
        }
        return 0;
    }

    @Override
    public int update(MANAGE_USEREntity entity) {
        StringBuffer sql = new StringBuffer();

        sql.append(" UPDATE MANAGE_USER SET ");
        sql.append(" NO =:NO,");
        sql.append(" NAME =:NAME,");
        sql.append(" PHONE =:PHONE,");
        sql.append(" EMAIL =:EMAIL,");
        sql.append(" STOPFLAG =:STOPFLAG,");
        sql.append(" LOGINNAME =:LOGINNAME,");
        sql.append(" PASSWORD =:PASSWORD,");
        sql.append(" AREAICODE =:AREAICODE ");
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
            String iCode = obj.containsKey("ICODE") ? obj.getString("ICODE") : "";
            if (null != iCode && iCode.length() > 0) {
                sb.append(" ICODE='" + obj.get("ICODE") + "' ");
                return sb.toString();
            }
        }

        //前台页面 默认加载数据也是 默认触发查询按钮产生的，所以，查询条件肯定是有的了。
        if (null != condition && !condition.isEmpty()) {
            JSONObject obj = JSONObject.fromObject(condition);

            String LOGINNAME = obj.containsKey("LOGINNAME") ? obj.getString("LOGINNAME") : "";
            if (null != LOGINNAME && !LOGINNAME.isEmpty()) {
                sb.append(" LOGINNAME = '" + LOGINNAME + "' ");
            }

            String PASSWORD = obj.containsKey("PASSWORD") ? obj.getString("PASSWORD") : "";
            if (null != PASSWORD && !PASSWORD.isEmpty()) {
                if (sb.length() > 0)
                    sb.append(" and ");
                sb.append(" PASSWORD = '" + PASSWORD + "' ");
            }
        }

        return sb.toString();
    }

    @Override
    public List<Map<String, Object>> findByCondition(String condition, int start, int count) {
        condition = buildCondition(condition);
        StringBuffer sql = new StringBuffer();
        sql.append("                    select ");
        sql.append("               		MANAGE_USER.*, row_number() OVER(ORDER BY null) AS row_number ");
        sql.append("               		from MANAGE_USER ");
        if (null != condition && condition.length() > 0) {
            sql.append(" where " + condition);
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
        sql.append(" select count(1) from MANAGE_USER ");
        if (null != condition && condition.length() > 0)
            sql.append(" where " + condition);
        return namedjdbcTemplate.getJdbcOperations().queryForInt(sql.toString());
    }

    @Override
    public int delete(String uuid) {
        StringBuffer sql = new StringBuffer();
        sql.append(" delete from MANAGE_USER ");
        sql.append(" where ICODE =:ICODE");
        Map paramMap = new HashMap();
        paramMap.put("ICODE", uuid);
        return namedjdbcTemplate.update(sql.toString(), paramMap);
    }


    /**
     * 人员列表
     */
    @Override
    public List<Map<String, Object>> findGridByCondition(String condition) {
        condition = buildCondition(condition);

        //查询第一级的数据
        StringBuffer sql = new StringBuffer();
        sql.append("                    select ");
        sql.append("               		ICODE AREAICODE,NAME,'AREA'||ICODE ICODE,'' NO,'' PHONE,'' EMAIL,0 STOPFLAG,'' LOGINNAME,'' PASSWORD,'icon-ok' iconCls, row_number() OVER(ORDER BY null) AS row_number ");
        sql.append("               		from MANAGE_AREA ");
        //取登录单位
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession(true);

        MANAGE_AREAEntity areadata = (MANAGE_AREAEntity) session.getAttribute("area");
        if (null != areadata && areadata.getICODE().length() > 0) {
            sql.append(" where ICODE='" + areadata.getICODE() + "' ");
        } else {
            sql.append(" where PARENTICODE is null ");
        }
        if (null != condition && condition.length() > 0) {
            sql.append(" and " + condition);
        }
        logger.info(sql.toString());
        List<Map<String, Object>> list = namedjdbcTemplate.getJdbcOperations().queryForList(sql.toString());

        //循环查询结果,查询下一级数据
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                findSubGridByCondition(condition, list.get(i).get("AREAICODE").toString(), list.get(i));
            }
        }
        return list;
    }

    public void findSubGridByCondition(String condition, String parenticode, Map<String, Object> map) {

        //查询数据
        StringBuffer sql = new StringBuffer();
        sql.append("                    select ");
        sql.append("               		MANAGE_USER.AREAICODE,MANAGE_USER.ICODE,MANAGE_USER.NO,MANAGE_USER.NAME,MANAGE_USER.PHONE,MANAGE_USER.EMAIL,MANAGE_USER.STOPFLAG,MANAGE_USER.LOGINNAME,MANAGE_USER.PASSWORD,'' iconCls, row_number() OVER(ORDER BY null) AS row_number ");
        sql.append("               		from MANAGE_USER INNER JOIN MANAGE_AREA ON MANAGE_USER.AREAICODE=MANAGE_AREA.ICODE ");
        sql.append("               		where MANAGE_USER.AREAICODE='" + parenticode + "' ");
        if (null != condition && condition.length() > 0) {
            sql.append("                AND " + condition);
        }
        sql.append("                    union all ");
        sql.append("                    select ");
        sql.append("               		ICODE AREAICODE,'AREA'||ICODE ICODE,'' NO,NAME,'' PHONE,'' EMAIL,0 STOPFLAG,'' LOGINNAME,'' PASSWORD,'icon-ok' iconCls, row_number() OVER(ORDER BY null) AS row_number ");
        sql.append("               		from MANAGE_AREA ");
        sql.append("               		where PARENTICODE='" + parenticode + "' ");
        if (null != condition && condition.length() > 0)
            sql.append("                AND " + condition);
        logger.info(sql.toString());
        List<Map<String, Object>> list = namedjdbcTemplate.getJdbcOperations().queryForList(sql.toString());

        //如果结果数大于0，则加入上级，并且继续循环查询下级
        if (list.size() > 0) {
            map.put("children", list);
            String icon = "";
            for (int i = 0; i < list.size(); i++) {
                if (null != list.get(i).get("iconCls") && !list.get(i).get("iconCls").toString().isEmpty()) {
                    icon = list.get(i).get("iconCls").toString();
                    findSubGridByCondition(condition, list.get(i).get("AREAICODE").toString(), list.get(i));
                }

                //设置图标
                list.get(i).remove("ICONCLS");
                if (null != icon && !icon.isEmpty())
                    list.get(i).put("iconCls", "icon-folder");
                else
                    list.get(i).put("iconCls", "icon-user");
            }
        }
    }

    @Override
    public List<Map<String, Object>> findByLOGINNAME(String condition, int start, int count) {
        StringBuffer sql = new StringBuffer();
        sql.append("                    select ");
        sql.append("               		MANAGE_USER.*, row_number() OVER(ORDER BY null) AS row_number ");
        sql.append("               		from MANAGE_USER ");
        if (null != condition && condition.length() > 0)
            sql.append("                where  1=1 " + condition);
        sql = pageSql(sql, start, count);
        logger.info(sql.toString());
        List<Map<String, Object>> list = namedjdbcTemplate.getJdbcOperations().queryForList(sql.toString());
        return list;
    }

    @Override
    public int countByLOGINNAME(String condition) {
        StringBuffer sql = new StringBuffer();
        sql.append(" select count(1) from MANAGE_USER ");
        if (null != condition && condition.length() > 0) {
            sql.append(" where 1=1 " + condition);
        }
        return this.namedjdbcTemplate.getJdbcOperations().queryForInt(sql.toString());
    }

}
