package com.manage.platform.dao.impl;

import com.manage.platform.dao.IMANAGE_AREADao;
import com.manage.platform.entity.MANAGE_AREAEntity;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MANAGE_AREADaoImpl extends DaoImplBase implements IMANAGE_AREADao {

    @Override
    public int insert(MANAGE_AREAEntity entity) {
        try {
            String sql = "insert into MANAGE_AREA(ICODE,NO,NAME,SPELLNO,CUSTOMNO,PARENTICODE,STOPFLAG,ADDRESS,ZIP,TEL,LEVEL1)" +
                    " VALUES(:ICODE,:NO,:NAME,:SPELLNO,:CUSTOMNO,:PARENTICODE,:STOPFLAG,:ADDRESS,:ZIP,:TEL,:LEVEL1)";
            SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(entity);
            return this.namedjdbcTemplate.update(sql, namedParameters);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return 0;
    }

    @Override
    public int update(MANAGE_AREAEntity entity) {
        StringBuffer sql = new StringBuffer();

        sql.append(" UPDATE MANAGE_AREA SET ");
        sql.append(" NO			 =:NO			,");
        sql.append(" NAME			 =:NAME			,");
        sql.append(" SPELLNO		 =:SPELLNO		,");
        sql.append(" CUSTOMNO		 =:CUSTOMNO		,");
        sql.append(" PARENTICODE	 =:PARENTICODE	,");
        sql.append(" STOPFLAG		 =:STOPFLAG		,");
        sql.append(" ADDRESS		 =:ADDRESS		,");
        sql.append(" ZIP			 =:ZIP			,");
        sql.append(" TEL			 =:TEL			,");
        sql.append(" LEVEL1			 =:LEVEL1		 ");
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
        if (null != condition && !condition.isEmpty()) {
        }
        return sb.toString();
    }

    @Override
    public List<Map<String, Object>> findByCondition(String condition, int start, int count) {
        condition = buildCondition(condition);
        StringBuffer sql = new StringBuffer();
        sql.append("                    select ");
        sql.append("               		MANAGE_AREA.*, row_number() OVER(ORDER BY null) AS row_number ");
        sql.append("               		from MANAGE_AREA ");
        if (null != condition && condition.length() > 0) {
            sql.append("                where " + condition);
        }
        sql = pageSql(sql, start, count);
        logger.info(sql.toString());
        List<Map<String, Object>> list = namedjdbcTemplate.getJdbcOperations().queryForList(sql.toString());
        return list;
    }

    @Override
    public List<Map<String, Object>> findGridByCondition(String condition) {

        //查询第一级的数据
        StringBuffer sql = new StringBuffer();
        sql.append("                    select ");
        sql.append("               		MANAGE_AREA.*, row_number() OVER(ORDER BY null) AS row_number ");
        sql.append("               		from MANAGE_AREA ");

        //取登录单位
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession(true);
        MANAGE_AREAEntity areadata = (MANAGE_AREAEntity) session.getAttribute("area");
        if (null != areadata && areadata.getICODE().length() > 0)
            sql.append(" where ICODE='" + areadata.getICODE() + "' ");
        else
            sql.append(" where PARENTICODE is null ");
        if (null != condition && condition.length() > 0)
            sql.append("                AND " + condition);

        logger.info(sql.toString());
        List<Map<String, Object>> list = namedjdbcTemplate.getJdbcOperations().queryForList(sql.toString());
        //循环查询结果,查询下一级数据
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                this.findSubGridByCondition(condition, list.get(i).get("ICODE").toString(), list.get(i));
            }
        }
        return list;
    }

    public void findSubGridByCondition(String condition, String parenticode, Map<String, Object> map) {
        //查询数据
        StringBuffer sql = new StringBuffer();
        sql.append("                    select ");
        sql.append("               		MANAGE_AREA.*, row_number() OVER(ORDER BY null) AS row_number ");
        sql.append("               		from MANAGE_AREA ");
        sql.append("               		where PARENTICODE='" + parenticode + "' ");
        if (null != condition && condition.length() > 0){
            sql.append(" and "+condition );
        }
        logger.info(sql.toString());
        List<Map<String, Object>> list = namedjdbcTemplate.getJdbcOperations().queryForList(sql.toString());
        if(list.size()>0){
            map.put("children", list);
            for(int i=0;i<list.size();i++){
                findSubGridByCondition(condition,list.get(i).get("ICODE").toString(), list.get(i));
            }
        }
    }

    @Override
    public List<Map<String, Object>> findTreeByCondition(String condition) {
        condition = buildCondition(condition);

        //查询第一级的数据
        StringBuffer sql = new StringBuffer();
        sql.append("                    select ");
        sql.append("               		ICODE,NAME ");
        sql.append("               		from MANAGE_AREA ");

        //取登录单位
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = (request).getSession(true);
        MANAGE_AREAEntity areadata = (MANAGE_AREAEntity)session.getAttribute("area");
        if(null!=areadata && areadata.getICODE().length()>0)
            sql.append(" where ICODE='"+areadata.getICODE()+"' ");
        else
            sql.append(" where PARENTICODE is null ");
        if (null != condition && condition.length() > 0)
            sql.append("                AND " + condition);
        logger.info(sql.toString());
        List<Map<String, Object>> list = namedjdbcTemplate.getJdbcOperations().queryForList(sql.toString());

        //循环查询结果,查询下一级数据
        if(list.size()>0){
            String id="";
            String text="";
            for (int i = 0; i < list.size(); i++) {
                id=list.get(i).get("ICODE").toString();
                //处理一个奇怪的问题，三级的汉字就显示不出来，所以加一个空格
                text = list.get(i).get("NAME").toString()+" ";
                list.get(i).remove("ICODE");
                list.get(i).remove("NAME");

                list.get(i).put("id",id);
                list.get(i).put("text",text);

                findSubTreeByCondition(condition,list.get(i).get("id").toString(),list.get(i));
            }
        }
        return list;
    }

    public void findSubTreeByCondition(String condition,String parenticode,Map<String, Object> map ) {

        //查询数据
        StringBuffer sql = new StringBuffer();
        sql.append("                    select ");
        sql.append("               		ICODE,NAME ");
        sql.append("               		from MANAGE_AREA ");
        sql.append("               		where PARENTICODE='"+parenticode+"' ");
        if (null != condition && condition.length() > 0)
            sql.append("                AND " + condition);
        logger.info(sql.toString());
        List<Map<String, Object>> list = namedjdbcTemplate.getJdbcOperations().queryForList(sql.toString());

        //如果结果数大于0，则加入上级，并且继续循环查询下级
        if(list.size()>0){
            map.put("children", list);

            String id = "";
            String text = "";
            for (int i = 0; i < list.size(); i++) {
                id = list.get(i).get("ICODE").toString();
                //处理一个奇怪的问题，三级的汉字就显示不出来，所以加一个空格
                text = list.get(i).get("NAME").toString()+" ";

                list.get(i).remove("ICODE");
                list.get(i).remove("NAME");

                list.get(i).put("id",id);
                list.get(i).put("text",text);

                findSubTreeByCondition(condition,list.get(i).get("id").toString(),list.get(i));
            }
        }
    }

    @Override
    public int countByCondition(String condition) {
        condition = buildCondition(condition);
        StringBuffer sql = new StringBuffer();
        sql.append(" select count(1) from MANAGE_AREA ");
        if (null != condition && condition.length() > 0)
            sql.append(" where " + condition);
        return namedjdbcTemplate.getJdbcOperations().queryForInt(sql.toString());
    }

    @Override
    public int delete(String uuid) {
        StringBuffer sql = new StringBuffer();
        sql.append(" delete from MANAGE_AREA ");
        sql.append("where ICODE =:ICODE");
        Map paramMap = new HashMap();
        paramMap.put("ICODE", uuid);
        return this.namedjdbcTemplate.update(sql.toString(), paramMap);
    }

}
