package com.manage.platform.dao.impl;

import com.manage.platform.dao.IMANAGE_MODELDao;
import com.manage.platform.entity.MANAGE_MODELEntity;
import net.sf.json.JSONObject;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MANAGE_MODELDaoImpl  extends DaoImplBase implements IMANAGE_MODELDao {

    public int insert(MANAGE_MODELEntity entity) {
        try {
            String sql = "insert into MANAGE_MODEL(ICODE,NAME,URL,PARENTICODE,LEVEL1)" +
                    " VALUES(:ICODE,:NAME,:URL,:PARENTICODE,:LEVEL1)";
            SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(entity);
            return this.namedjdbcTemplate.update(sql, namedParameters);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return 0;
    }

    public int update(MANAGE_MODELEntity entity) {
        StringBuffer  sql = new StringBuffer();

        sql.append(" UPDATE MANAGE_MODEL SET ");
        sql.append(" NAME			 =:NAME			,");
        sql.append(" URL		     =:URL		,");
        sql.append(" PARENTICODE	 =:PARENTICODE	,");
        sql.append(" LEVEL1			 =:LEVEL1		 ");
        sql.append(" WHERE ICODE=:ICODE");

        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(entity);
        return this.namedjdbcTemplate.update(sql.toString(), namedParameters);
    }

    private String buildCondition(String condition){
        StringBuffer sb = new StringBuffer();

        //=========================================================================
        // 按主键查询
        //=========================================================================
        if(null!=condition && !condition.isEmpty()){
            JSONObject obj = JSONObject.fromObject(condition);
            String ICODE =obj.containsKey("ICODE")? obj.getString("ICODE"):"";
            if(null!=ICODE  && ICODE .length()>0){
                sb.append(" ICODE='"+obj.get("ICODE")+"' ");
                return sb.toString();
            }
        }

        return sb.toString();
    }

    @Override
    public List<Map<String, Object>> findByCondition(String condition, int start, int count) {
        condition = buildCondition(condition);
        StringBuffer sql = new StringBuffer();
        sql.append("                    select ");
        sql.append("               		MANAGE_MODEL.*, row_number() OVER(ORDER BY null) AS row_number ");
        sql.append("               		from MANAGE_MODEL ");
        if (null != condition && condition.length() > 0)
            sql.append("                where " + condition);
        sql = pageSql(sql, start, count);
        logger.info(sql.toString());
        List<Map<String, Object>> list = namedjdbcTemplate.getJdbcOperations().queryForList(sql.toString());
        return list;
    }

    @Override
    public List<Map<String, Object>> findGridByCondition(String condition) {
        condition = buildCondition(condition);

        //查询第一级的数据
        StringBuffer sql = new StringBuffer();
        sql.append("                    select ");
        sql.append("               		MANAGE_MODEL.*, row_number() OVER(ORDER BY null) AS row_number ");
        sql.append("               		from MANAGE_MODEL ");
        sql.append("               		where PARENTICODE is null ");
        if (null != condition && condition.length() > 0)
            sql.append("                AND " + condition);
        logger.info(sql.toString());
        List<Map<String, Object>> list = namedjdbcTemplate.getJdbcOperations().queryForList(sql.toString());

        //循环查询结果,查询下一级数据
        if(list.size()>0){
            for (int i = 0; i < list.size(); i++) {
                findSubGridByCondition(condition,list.get(i).get("ICODE").toString(),list.get(i));
            }
        }

        return list;
    }

    public void findSubGridByCondition(String condition,String parenticode,Map<String, Object> map ) {

        //查询数据
        StringBuffer sql = new StringBuffer();
        sql.append("                    select ");
        sql.append("               		MANAGE_MODEL.*, row_number() OVER(ORDER BY null) AS row_number ");
        sql.append("               		from MANAGE_MODEL ");
        sql.append("               		where PARENTICODE='"+parenticode+"' ");
        if (null != condition && condition.length() > 0)
            sql.append("                AND " + condition);
        logger.info(sql.toString());
        List<Map<String, Object>> list = namedjdbcTemplate.getJdbcOperations().queryForList(sql.toString());

        //如果结果数大于0，则加入上级，并且继续循环查询下级
        if(list.size()>0){
            map.put("children", list);
            for (int i = 0; i < list.size(); i++) {
                findSubGridByCondition(condition,list.get(i).get("ICODE").toString(),list.get(i));
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
        sql.append("               		from MANAGE_MODEL ");
        sql.append("               		where PARENTICODE is null ");
        if (null != condition && condition.length() > 0)
            sql.append("                AND " + condition);
        logger.info(sql.toString());
        List<Map<String, Object>> list = namedjdbcTemplate.getJdbcOperations().queryForList(sql.toString());

        //循环查询结果,查询下一级数据
        if(list.size()>0){
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

        return list;
    }

    public void findSubTreeByCondition(String condition,String parenticode,Map<String, Object> map ) {

        //查询数据
        StringBuffer sql = new StringBuffer();
        sql.append("                    select ");
        sql.append("               		ICODE,NAME ");
        sql.append("               		from MANAGE_MODEL ");
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
        sql.append(" select count(1) from MANAGE_MODEL ");
        if (null != condition && condition.length() > 0)
            sql.append(" where " + condition);
        return namedjdbcTemplate.getJdbcOperations().queryForInt(sql.toString());
    }

    @Override
    public int delete(String uuid) {
        StringBuffer sql = new StringBuffer();

        sql.append(" delete from MANAGE_MODEL ");
        sql.append(" where ICODE =:ICODE");

        Map paramMap = new HashMap();
        paramMap.put("ICODE", uuid);
        return namedjdbcTemplate.update(sql.toString(), paramMap);
    }

    /**
     * 获取登录账号的菜单
     */
    @Override
    public List<Map<String, Object>> findLgoinMenu(String loginusericode, String modellevel, String modelparenticode) {

        StringBuffer sql = new StringBuffer();

        if(modellevel.equals("一级菜单")){
            sql.append(" select distinct m1.icode,m1.name,m1.url " +
                    " from manage_user " +
                    " inner join manage_user_role on manage_user.icode=manage_user_role.usericode " +
                    " inner join manage_permission on manage_user_role.roleicode=manage_permission.roleicode " +
                    " inner join manage_model m3 on manage_permission.modelicode = m3.icode " +
                    " inner join manage_model m2 on m3.parenticode=m2.icode " +
                    " inner join manage_model m1 on m2.parenticode=m1.icode " +
                    " where manage_user.icode='"+loginusericode+"' " +
                    " and m3.level1 like '具体功能菜单%' ");
        }
        if(modellevel.equals("二级菜单")){
            sql.append(" select distinct m2.icode,m2.name,m2.url from manage_user " +
                    " inner join manage_user_role on manage_user.icode=manage_user_role.usericode " +
                    " inner join manage_permission on manage_user_role.roleicode=manage_permission.roleicode " +
                    " inner join manage_model m3 on manage_permission.modelicode = m3.icode " +
                    " inner join manage_model m2 on m3.parenticode=m2.icode " +
                    " where manage_user.icode='"+loginusericode+"' " +
                    " and m3.level1 like '具体功能菜单%' "+
                    " and m2.parenticode='"+modelparenticode+"' ");
        }
        if(modellevel.equals("具体功能菜单")){
            sql.append(" select distinct manage_model.icode,manage_model.name,manage_model.url from manage_user " +
                    " inner join manage_user_role on manage_user.icode=manage_user_role.usericode " +
                    " inner join manage_permission on manage_user_role.roleicode=manage_permission.roleicode " +
                    " inner join manage_model on manage_permission.modelicode = manage_model.icode " +
                    " where manage_user.icode='"+loginusericode+"' " +
                    " and manage_model.level1 like '具体功能菜单%' " +
                    " and manage_model.parenticode='"+modelparenticode+"' ");
        }

        logger.info(sql.toString());
        List<Map<String, Object>> list = namedjdbcTemplate.getJdbcOperations().queryForList(sql.toString());

        String id = "";
        String text = "";
        String url = "";
        for (int i = 0; i < list.size(); i++) {
            id = list.get(i).get("ICODE").toString();
            text = list.get(i).get("NAME").toString();
            url = null==list.get(i).get("URL")?"":list.get(i).get("URL").toString();

            list.get(i).remove("ICODE");
            list.get(i).remove("NAME");

            list.get(i).put("id",id);
            list.get(i).put("text",text);

            if(url.length()>0){
                Map<String,Object> map111 = new HashMap<String,Object>();
                map111.put("href", url);
                list.get(i).put("attributes", map111);
            }

        }

        return list;
    }

}
