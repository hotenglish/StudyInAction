package com.manage.platform.action;

import com.fore.util.JsonUtil;
import com.fore.util.ReadUrlString;
import com.manage.platform.entity.MANAGE_USER_ROLEEntity;
import com.manage.platform.service.IMANAGE_ROLEService;
import com.manage.platform.service.IMANAGE_USER_ROLEService;
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

public class MANAGE_USER_ROLEAction extends ActionBase implements Action {

    private IMANAGE_USER_ROLEService imanage_USER_ROLEservice;
    private IMANAGE_ROLEService imanage_roleservice;

    public IMANAGE_USER_ROLEService getImanage_USER_ROLEservice() {
        return imanage_USER_ROLEservice;
    }

    public void setImanage_USER_ROLEservice(IMANAGE_USER_ROLEService imanage_USER_ROLEservice) {
        this.imanage_USER_ROLEservice = imanage_USER_ROLEservice;
    }

    public IMANAGE_ROLEService getImanage_roleservice() {
        return imanage_roleservice;
    }

    public void setImanage_roleservice(IMANAGE_ROLEService imanage_roleservice) {
        this.imanage_roleservice = imanage_roleservice;
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

        List<Map<String, Object>> list = imanage_USER_ROLEservice.findByCondition(condition, start, pageCount);
        JSONArray jsonList = JsonUtil.fromObject(list);
        dataMap.put("rows", jsonList);

        int count = imanage_USER_ROLEservice.countByCondition(condition);
        dataMap.put("total", count);

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

        //找打用户角色关系列表
        condition = "{'USERICODE' : '" + maindatauuid + "'}";
        List<Map<String, Object>> listUserRole = imanage_USER_ROLEservice.findByCondition(condition, 0, 1000);

        //找打所有角色
        List<Map<String, Object>> listRole = imanage_roleservice.findByCondition("", 0, 1000);

        //循环设置checked
        String ROLEICODE = "";
        String ROLEICODE1 = "";
        for (int i = 0; i < listRole.size(); i++) {
            Map<String, Object> mapRole = listRole.get(i);
            ROLEICODE = mapRole.get("ICODE").toString();
            for (int j = 0; j < listUserRole.size(); j++) {
                Map<String, Object> mapUserRole = listUserRole.get(j);
                ROLEICODE1 = mapUserRole.get("ROLEICODE").toString();
                if (ROLEICODE.equals(ROLEICODE1)) {
                    mapRole.put("checked", true);
                    break;
                }
            }
        }

        JSONArray jsonlist = JsonUtil.fromObject(listRole);

        dataMap.put("rows", jsonlist);
        dataMap.put("total", jsonlist.size());

        logger.info(jsonlist.toString());

        // 清空查询条件
        condition = null;
        return SUCCESS;
    }


    /**
     * 保存表单信息功能
     */
    public String save() {
        try {
            //获取参数
            HttpServletRequest request = ServletActionContext.getRequest();
            ReadUrlString urlString = new ReadUrlString();
            String dataString = urlString.streamToString(request.getReader());
            String jsonString = URLDecoder.decode(dataString, "UTF-8");

            //拆分参数
            JSONObject obj = JSONObject.fromObject(jsonString);
            String USERICODE = obj.containsKey("USERICODE") ? obj.getString("USERICODE") : "";
            String ROLES = obj.containsKey("ROLES") ? obj.getString("ROLES") : "";
            int returnCount = 0;
            if (null != USERICODE && USERICODE.length() > 0) {

                //删除旧数据
                imanage_USER_ROLEservice.deleteByUsericode(USERICODE);

                //增加新数据
                String[] modelarr = ROLES.split("\\|");
                for (int i = 0; i < modelarr.length; i++) {
                    MANAGE_USER_ROLEEntity entity = new MANAGE_USER_ROLEEntity();
                    entity.setICODE(UUID.randomUUID().toString());
                    entity.setUSERICODE(USERICODE);
                    entity.setROLEICODE(modelarr[i]);
                    returnCount += imanage_USER_ROLEservice.insert(entity);
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
        int returncount = imanage_USER_ROLEservice.delete(maindatauuid);

        dataMap.put("returncount", returncount);
        return SUCCESS;
    }

}
