package com.manage.platform.action;

import com.fore.util.JsonUtil;
import com.fore.util.ReadUrlString;
import com.manage.platform.entity.MANAGE_AREAEntity;
import com.manage.platform.entity.MANAGE_USEREntity;
import com.manage.platform.service.IMANAGE_AREAService;
import com.manage.platform.service.IMANAGE_USERService;
import com.opensymphony.xwork2.Action;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class MANAGE_USERAction extends ActionBase implements Action {

    private IMANAGE_USERService imanage_userservice;
    private IMANAGE_AREAService imanage_areaservice;

    public IMANAGE_USERService getImanage_userservice() {
        return imanage_userservice;
    }

    public void setImanage_userservice(IMANAGE_USERService imanage_userservice) {
        this.imanage_userservice = imanage_userservice;
    }

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
     * 登录
     */
    public String login() {
        // 条件参数
        if (null != condition && condition.length() > 0) {
            try {
                condition = java.net.URLDecoder.decode(condition, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        // 查询数据库
        logger.info(condition);
        List<Map<String, Object>> userList = imanage_userservice.findByCondition(condition, 0, 1);
        JSONArray jsonUserList = JsonUtil.fromObject(userList);
        dataMap.put("rows", jsonUserList);
        dataMap.put("total", jsonUserList.size());
        logger.info(dataMap.toString());

        if (jsonUserList.size() > 0) {
            String userDataJsonStr = jsonUserList.get(0).toString();
            MANAGE_USEREntity userData = (MANAGE_USEREntity) JsonUtil.toBean(userDataJsonStr, MANAGE_USEREntity.class);
            HttpServletRequest request = ServletActionContext.getRequest();
            HttpSession session = request.getSession(true);
            session.setAttribute("user", userData);

            //查找地域信息并写入session
            condition = "{'ICODE':'" + userData.getAREAICODE() + "'}";
            List<Map<String, Object>> arealist = imanage_areaservice.findByCondition(condition, 0, 1);
            JSONArray jsonAreaList = JsonUtil.fromObject(arealist);
            if (jsonAreaList.size() > 0) {
                String areaDataJsonStr = jsonAreaList.get(0).toString();
                MANAGE_AREAEntity areaData = (MANAGE_AREAEntity) JsonUtil.toBean(areaDataJsonStr, MANAGE_AREAEntity.class);
                session.setAttribute("area", areaData);
            }
        }

        // 清空查询条件
        condition = null;
        return SUCCESS;
    }


    /**
     * 查询treegrid
     */
    public String findgrid() {
        // 条件参数
        if (null != condition && condition.length() > 0) {
            try {
                condition = java.net.URLDecoder.decode(condition, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        // 查询数据库
        logger.info(condition);
        List<Map<String, Object>> list = imanage_userservice.findGridByCondition(condition);
        JSONArray jsonList = JsonUtil.fromObject(list);
        jsonarr = jsonList;
        logger.info(jsonList.toString());

        // 清空查询条件
        condition = null;
        return SUCCESS;
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
        List<Map<String, Object>> list = imanage_userservice.findByCondition(condition, start, pageCount);
        JSONArray jsonList = JsonUtil.fromObject(list);
        dataMap.put("rows", jsonList);
        int count = imanage_userservice.countByCondition(condition);
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
            MANAGE_USEREntity maindata = (MANAGE_USEREntity) JsonUtil.toBean(jsonString, MANAGE_USEREntity.class);
            if (null == maindata.getICODE() || maindata.getICODE().isEmpty()) {
                maindata.setICODE(UUID.randomUUID().toString());
                //公用字段
                //InitCreate(maindata);
                int returnCount = imanage_userservice.insert(maindata);
                dataMap.put("maindatauuid", maindata.getICODE());
                dataMap.put("savetype", "insert");
                dataMap.put("returncount", returnCount);
            } else {
                //公用字段
                //InitModidy(maindata);
                int returnCount = imanage_userservice.update(maindata);
                dataMap.put("savetype", "update");
                dataMap.put("returncount", returnCount);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    /**
     * 修改密码
     */
    public String changePassword() {
        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            ReadUrlString readUrlString = new ReadUrlString();
            String dataString = readUrlString.streamToString(request.getReader());
            String jsonString = URLDecoder.decode(dataString, "UTF-8");

            //分拆获取到的输入信息
            JSONObject obj = JSONObject.fromObject(jsonString);
            String OLDPASSWORD = obj.containsKey("OLDPASSWORD") ? obj.getString("OLDPASSWORD") : "";
            String NEWPASSWORD = obj.containsKey("NEWPASSWORD") ? obj.getString("NEWPASSWORD") : "";

            //session中的原密码
            HttpSession session = request.getSession(true);
            MANAGE_USEREntity userdata = (MANAGE_USEREntity) session.getAttribute("user");

            //判断原密码是否正确
            if (!OLDPASSWORD.equals(userdata.getPASSWORD())) {
                dataMap.put("msg", "原密码输入不正确，请重新输入！");
                dataMap.put("returncount", 0);
            } else {
                //保存新密码
                userdata.setPASSWORD(NEWPASSWORD);
                int returnCount = imanage_userservice.update(userdata);
                dataMap.put("msg", "密码修改成功！");
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
        int returnCount = imanage_userservice.delete(maindatauuid);
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
        List<Map<String, Object>> list = imanage_userservice.findByCondition(condition, 0, 1);
        condition = null;
        int returnsize = 0;
        if (null != list) {
            returnsize = list.size();
            System.out.println(returnsize);
            if (returnsize > 0) {
                //主数据记录
                JSONArray jsonList = JsonUtil.fromObject(list);
                JSONObject maindata = jsonList.getJSONObject(0);
                dataMap.put("maindata", maindata);
            }
        }

        return SUCCESS;
    }

    /**
     * 查询和分页功能
     */
    public String USERFindKK() {

        // 当前页
        int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);
        // 每页显示条数
        int pageCount = Integer.parseInt((rows == null || page == "0") ? "20" : rows);

        int start = (intPage - 1) * pageCount + 1;

        // 查询数据库
        logger.info(condition);
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession(true);
        MANAGE_USEREntity user = (MANAGE_USEREntity) session.getAttribute("user");
        List<Map<String, Object>> list = imanage_userservice.findByLOGINNAME(" and loginname like '%kk%' and AREAICODE='" + user.AREAICODE + "'", start, pageCount);
        JSONArray jsonList = JsonUtil.fromObject(list);
        dataMap.put("rows", jsonList);

        int count = imanage_userservice.countByLOGINNAME("and loginname like '%kk%' and AREAICODE='" + user.AREAICODE + "'");
        dataMap.put("total", count);
        logger.info(dataMap.toString());

        // 清空查询条件
        condition = null;
        return SUCCESS;
    }
}
