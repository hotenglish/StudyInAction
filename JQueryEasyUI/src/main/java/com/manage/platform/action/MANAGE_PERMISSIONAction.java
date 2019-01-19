package com.manage.platform.action;

import com.fore.util.JsonUtil;
import com.fore.util.ReadUrlString;
import com.manage.platform.entity.MANAGE_PERMISSIONEntity;
import com.manage.platform.service.IMANAGE_MODELService;
import com.manage.platform.service.IMANAGE_PERMISSIONService;
import com.opensymphony.xwork2.Action;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class MANAGE_PERMISSIONAction extends ActionBase implements Action {

    private IMANAGE_PERMISSIONService imanage_PERMISSIONservice;
    private IMANAGE_MODELService imanage_modelservice;

    public IMANAGE_PERMISSIONService getImanage_PERMISSIONservice() {
        return imanage_PERMISSIONservice;
    }

    public void setImanage_PERMISSIONservice(IMANAGE_PERMISSIONService imanage_PERMISSIONservice) {
        this.imanage_PERMISSIONservice = imanage_PERMISSIONservice;
    }

    public IMANAGE_MODELService getImanage_modelservice() {
        return imanage_modelservice;
    }

    public void setImanage_modelservice(IMANAGE_MODELService imanage_modelservice) {
        this.imanage_modelservice = imanage_modelservice;
    }

    @Override
    public String execute() throws Exception {
        return null;
    }

    /**
     * 查询和分页功能
     */
    public String find() {

        // 当前页
        int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);

        // 每页显示条数
        int pageCount = Integer.parseInt((rows == null || rows == "0") ? "20" : rows);
        int start = (intPage - 1) * pageCount + 1;

        // 参数
        if (null != condition && condition.length() > 0) {
            try {
                condition = java.net.URLDecoder.decode(condition, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        // 查询数据库
        logger.info(condition);
        List<Map<String, Object>> list = imanage_PERMISSIONservice.findByCondition(condition, start, pageCount);
        JSONArray jsonList = JSONArray.fromObject(list);
        dataMap.put("rows", jsonList);

        int count = imanage_PERMISSIONservice.countByCondition(condition);
        dataMap.put("total", list.size());

        logger.info(dataMap.toString());

        // 清空查询条件
        condition = null;
        return SUCCESS;
    }

    /**
     * 根据主键查找单条数据的功能
     */
    public String findByUUID() {
        if (null != maindatauuid && maindatauuid.length() > 0) {
            try {
                maindatauuid = java.net.URLDecoder
                        .decode(maindatauuid, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        //找打角色菜单关系列表
        condition = "{'ROLEICODE' : '" + maindatauuid + "'}";

        List<Map<String, Object>> listRoleModel = imanage_PERMISSIONservice.findByCondition(condition, 0, 1000);

        //找打所有菜单
        List<Map<String, Object>> listModel = imanage_modelservice.findTreeByCondition("");

        //循环设置checked
        setChecked(listRoleModel, listModel);

        JSONArray jsonlist = JsonUtil.fromObject(listModel);

        jsonarr = jsonlist;

        logger.info(jsonlist.toString());

        // 清空查询条件
        condition = null;
        return SUCCESS;
    }

    private void setChecked(List<Map<String, Object>> listRoleModel, List<Map<String, Object>> listModel) {
        String iCode = "";
        String modeliCode = "";
        List<Map<String, Object>> sublistmodel;
        for (int i = 0; i < listModel.size(); i++) {
            Map<String, Object> mapModel = listModel.get(i);
            iCode = mapModel.get("id").toString();

            //设置节点是否选中
            for (int j = 0; j < listRoleModel.size(); j++) {
                Map<String, Object> mapRoleModel = listRoleModel.get(i);
                modeliCode = mapRoleModel.get("MODELICODE").toString();
                if (iCode.equals(modeliCode)) {
                    mapModel.put("checked", true);
                    break;
                }
            }

            //是否有子节点，如果有，则循环处理
            if (mapModel.containsKey("children")) {
                sublistmodel = (List<Map<String, Object>>) mapModel.get("children");
                setChecked(listRoleModel, sublistmodel);
            }
        }
    }

    /**
     * 保存表单信息功能
     */
    public String save() {
        try {
            //获取参数
            HttpServletRequest request = ServletActionContext.getRequest();
            ReadUrlString readUrlString = new ReadUrlString();
            String dataString = readUrlString.streamToString(request.getReader());
            String jsonString = URLDecoder.decode(dataString, "UTF-8");

            //拆分参数
            JSONObject obj = JSONObject.fromObject(jsonString);
            String ROLEICODE = obj.containsKey("ROLEICODE") ? obj.getString("ROLEICODE") : "";
            String MODELS = obj.containsKey("MODELS") ? obj.getString("MODELS") : "";
            int returnCount = 0;
            if (null != ROLEICODE && ROLEICODE.length() > 0) {
                //删除旧数据
                imanage_PERMISSIONservice.deleteByRoleicode(ROLEICODE);
                //增加新数据
                String[] modelarr = MODELS.split("\\|");
                for (int i = 0; i < modelarr.length; i++) {
                    MANAGE_PERMISSIONEntity entity = new MANAGE_PERMISSIONEntity();
                    entity.setICODE(UUID.randomUUID().toString());
                    entity.setMODELICODE(modelarr[i]);
                    entity.setROLEICODE(ROLEICODE);
                    returnCount += imanage_PERMISSIONservice.insert(entity);
                }
            }
            dataMap.put("returncount", returnCount);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    /**
     * 删除单条数据功能
     */
    public String delete() {
        if (null != maindatauuid && maindatauuid.length() > 0) {
            try {
                maindatauuid = java.net.URLDecoder.decode(maindatauuid, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        int returncount = imanage_PERMISSIONservice.delete(maindatauuid);

        dataMap.put("returncount", returncount);
        return SUCCESS;
    }

}
