package com.manage.platform.action;

import com.fore.util.JsonUtil;
import com.fore.util.ReadUrlString;
import com.manage.platform.entity.MANAGE_ROLEEntity;
import com.manage.platform.service.IMANAGE_ROLEService;
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

public class MANAGE_ROLEAction extends ActionBase implements Action {

    private IMANAGE_ROLEService imanage_ROLEservice;

    public IMANAGE_ROLEService getImanage_ROLEservice() {
        return imanage_ROLEservice;
    }

    public void setImanage_ROLEservice(IMANAGE_ROLEService imanage_ROLEservice) {
        this.imanage_ROLEservice = imanage_ROLEservice;
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

        // 清空查询条件
        logger.info(condition);
        List<Map<String, Object>> list = imanage_ROLEservice.findByCondition(condition, start, pageCount);
        JSONArray jsonList = JsonUtil.fromObject(list);
        dataMap.put("rows", jsonList);

        int count = imanage_ROLEservice.countByCondition(condition);
        dataMap.put("total", count);

        logger.info(dataMap.toString());

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
            MANAGE_ROLEEntity maindata = (MANAGE_ROLEEntity) JsonUtil.toBean(jsonString, MANAGE_ROLEEntity.class);
            if (null == maindata.getICODE() || maindata.getICODE().isEmpty()) {
                maindata.setICODE(UUID.randomUUID().toString());
                //公用字段
                //InitCreate(maindata);
                int returnCount = imanage_ROLEservice.insert(maindata);
                dataMap.put("maindatauuid", maindata.getICODE());
                dataMap.put("savetype", "insert");
                dataMap.put("returncount", returnCount);
            } else {
                int returnCount = imanage_ROLEservice.update(maindata);
                dataMap.put("savetype", "update");
                dataMap.put("returncount", returnCount);
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
        int returnCount = imanage_ROLEservice.delete(maindatauuid);
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
        List<Map<String, Object>> list = imanage_ROLEservice.findByCondition(condition, 0, 1);
        condition = null;
        int returnsize = 0;
        if (null != list) {
            returnsize = list.size();
        }
        System.out.println(returnsize);
        if (returnsize > 0) {
            //主数据记录
            JSONArray jsonlist = JsonUtil.fromObject(list);
            JSONObject mainData = jsonlist.getJSONObject(0);
            dataMap.put("maindata", mainData);
        }
        return SUCCESS;
    }

}
