package com.manage.platform.action;

import com.fore.util.JsonUtil;
import com.fore.util.ReadUrlString;
import com.manage.platform.entity.MANAGE_AREAEntity;
import com.manage.platform.service.IMANAGE_AREAService;
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

public class MANAGE_AREAAction extends ActionBase implements Action {

    private IMANAGE_AREAService imanage_areaservice;

    public IMANAGE_AREAService getImanage_areaservice() {
        return imanage_areaservice;
    }

    public void setImanage_areaservice(IMANAGE_AREAService imanage_areaservice) {
        this.imanage_areaservice = imanage_areaservice;
    }

    @Override
    public String execute() throws Exception {
        return null;
    }

    /**
     * 查询treegrid
     */
    public String findgrid() {

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
        List<Map<String, Object>> list = imanage_areaservice.findGridByCondition(condition);
        JSONArray jsonList = JsonUtil.fromObject(list);

        jsonarr = jsonList;

        logger.info(jsonList.toString());

        // 清空查询条件
        condition = null;
        return SUCCESS;
    }

    /**
     * 查询tree
     */
    public String findtree() {

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
        List<Map<String, Object>> list = imanage_areaservice.findTreeByCondition(condition);
        JSONArray jsonList = JsonUtil.fromObject(list);

        jsonarr = jsonList;

        logger.info(jsonList.toString());

        // 清空查询条件
        condition = null;
        return SUCCESS;
    }

    /**
     * 保存表单信息功能
     */
    public String save() {
        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            ReadUrlString urlString = new ReadUrlString();
            String dataString = urlString.streamToString(request.getReader());
            String jsonString = URLDecoder.decode(dataString, "UTF-8");
            MANAGE_AREAEntity mainData = (MANAGE_AREAEntity) JsonUtil.toBean(jsonString, MANAGE_AREAEntity.class);
            if (null == mainData.getICODE() || mainData.getICODE().isEmpty()) {
                mainData.setICODE(UUID.randomUUID().toString());
                //公用字段
                //InitCreate(maindata);
                int returnCount = imanage_areaservice.insert(mainData);
                dataMap.put("maindatauuid", mainData.getICODE());
                dataMap.put("savetype", "insert");
                dataMap.put("returncount", returnCount);
            } else {
                //公用字段
                //InitModidy(maindata);
                int returncount = imanage_areaservice.update(mainData);
                dataMap.put("savetype", "update");
                dataMap.put("returncount", returncount);
            }
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
        int returnCount = imanage_areaservice.delete(maindatauuid);

        dataMap.put("returncount", returnCount);
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
        condition = "{'ICODE' : '" + maindatauuid + "'}";
        List<Map<String, Object>> list = imanage_areaservice.findByCondition(condition, 0, 1);
        condition = null;
        int returnSize = 0;
        if (null != list) {
            returnSize = list.size();
        }
        System.out.println(returnSize);
        if (returnSize > 0) {
            //主数据记录
            JSONArray jsonList = JsonUtil.fromObject(list);
            JSONObject jsonObject = jsonList.getJSONObject(0);
            dataMap.put("maindata", maindata);
        }
        return SUCCESS;
    }

}
