package com.manage.platform.action;

import com.fore.util.JsonUtil;
import com.fore.util.ReadUrlString;
import com.manage.platform.entity.MANAGE_MODELEntity;
import com.manage.platform.entity.MANAGE_USEREntity;
import com.manage.platform.service.IMANAGE_MODELService;
import com.opensymphony.xwork2.Action;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

public class MANAGE_MODELAction extends ActionBase implements Action {

    private IMANAGE_MODELService manage_modelService;

    public IMANAGE_MODELService getManage_modelService() {
        return manage_modelService;
    }

    public void setManage_modelService(IMANAGE_MODELService manage_modelService) {
        this.manage_modelService = manage_modelService;
    }

    @Override
    public String execute() throws Exception {
        return null;
    }

    /**
     * 获取登录操作员的一级菜单
     */
    public String findLgoinFirstMenu() {

        //获取登录账号
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession(true);
        MANAGE_USEREntity userData = (MANAGE_USEREntity) session.getAttribute("user");

        //要返回的列表
        List<Map<String, Object>> list = null;

        //admin特殊处理
        if (userData.getLOGINNAME().toLowerCase().equals("admin")) {
            list = new ArrayList<>();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", "1");
            map.put("text", "系统管理");
            list.add(map);
        } else {
            list = manage_modelService.findLgoinMenu(userData.getICODE(), "一级菜单", "");
        }

        JSONArray jsonList = JsonUtil.fromObject(list);

        jsonarr = jsonList;
        logger.info(jsonList.toString());

        return SUCCESS;
    }

    /**
     * 获取登录操作员的二三级菜单
     */
    public String findLgoinSecondMenu() {
        if (null != maindatauuid && maindatauuid.length() > 0) {
            try {
                maindatauuid = java.net.URLDecoder
                        .decode(maindatauuid, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        //获取登录账号
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = (request).getSession(true);
        MANAGE_USEREntity userdata = (MANAGE_USEREntity) session.getAttribute("user");

        //要返回的列表
        List<Map<String, Object>> list = null;

        //admin特殊处理
        if (userdata.getLOGINNAME().toLowerCase().equals("admin")) {
            list = new ArrayList<>();

            Map<String, Object> map1 = new HashMap<String, Object>();
            map1.put("id", "11");
            map1.put("text", "地市管理");
            list.add(map1);

            List<Map<String, Object>> list11 = new ArrayList<Map<String, Object>>();
            Map<String, Object> map11 = new HashMap<String, Object>();
            map11.put("id", "111");
            map11.put("text", "地市管理");
            Map<String, Object> map111 = new HashMap<String, Object>();
            map111.put("href", "framework/area.jsp");
            map11.put("attributes", map111);
            list11.add(map11);
            map1.put("children", list11);

            Map<String, Object> map2 = new HashMap<String, Object>();
            map2.put("id", "12");
            map2.put("text", "人员管理");
            list.add(map2);

            List<Map<String, Object>> list12 = new ArrayList<Map<String, Object>>();
            Map<String, Object> map12 = new HashMap<String, Object>();
            map12.put("id", "121");
            map12.put("text", "人员管理");
            Map<String, Object> map121 = new HashMap<String, Object>();
            map121.put("href", "framework/user.jsp");
            map12.put("attributes", map121);
            list12.add(map12);
            map2.put("children", list12);

            Map<String, Object> map3 = new HashMap<String, Object>();
            map3.put("id", "13");
            map3.put("text", "菜单管理");
            list.add(map3);

            List<Map<String, Object>> list13 = new ArrayList<Map<String, Object>>();
            Map<String, Object> map13 = new HashMap<String, Object>();
            map13.put("id", "131");
            map13.put("text", "菜单管理");
            Map<String, Object> map131 = new HashMap<String, Object>();
            map131.put("href", "framework/model.jsp");
            map13.put("attributes", map131);
            list13.add(map13);
            map3.put("children", list13);

            Map<String, Object> map4 = new HashMap<String, Object>();
            map4.put("id", "14");
            map4.put("text", "权限设置");
            list.add(map4);

            List<Map<String, Object>> list14 = new ArrayList<Map<String, Object>>();
            Map<String, Object> map14 = new HashMap<String, Object>();
            map14.put("id", "141");
            map14.put("text", "系统角色");
            Map<String, Object> map141 = new HashMap<String, Object>();
            map141.put("href", "framework/role.jsp");
            map14.put("attributes", map141);
            list14.add(map14);
            map4.put("children", list14);

            Map<String, Object> map5 = new HashMap<String, Object>();
            map5.put("id", "15");
            map5.put("text", "修改密码");
            list.add(map5);

            List<Map<String, Object>> list15 = new ArrayList<Map<String, Object>>();
            Map<String, Object> map15 = new HashMap<String, Object>();
            map15.put("id", "151");
            map15.put("text", "修改密码");
            Map<String, Object> map151 = new HashMap<String, Object>();
            map151.put("href", "framework/changepassword.jsp");
            map15.put("attributes", map151);
            list15.add(map15);
            map5.put("children", list15);
        } else {

            //查询二级菜单
            list = manage_modelService.findLgoinMenu(userdata.getICODE(), "二级菜单", maindatauuid);

            if (list.size() > 0) {
                //参数三级菜单
                String icode = "";
                for (int i = 0; i < list.size(); i++) {
                    icode = list.get(i).get("id").toString();
                    List<Map<String, Object>> subList = manage_modelService.findLgoinMenu(userdata.getICODE(), "具体功能菜单", icode);
                    if (subList.size() > 0) {
                        list.get(i).put("children", subList);
                    }
                }
            }

        }

        JSONArray jsonList = JsonUtil.fromObject(list);

        jsonarr = jsonList;
        logger.info(jsonList.toString());

        return SUCCESS;
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
        List<Map<String, Object>> list = manage_modelService.findGridByCondition(condition);
        JSONArray jsonlist = JsonUtil.fromObject(list);

        jsonarr = jsonlist;

        logger.info(jsonlist.toString());

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
        List<Map<String, Object>> list = manage_modelService.findTreeByCondition(condition);
        JSONArray jsonlist = JsonUtil.fromObject(list);

        jsonarr = jsonlist;

        logger.info(jsonlist.toString());

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
        condition = "{'ICODE' : '" + maindatauuid + "'}";
        List<Map<String, Object>> list = manage_modelService.findByCondition(condition, 0, 1);
        condition = null;
        int returnsize = 0;
        if (null != list) {
            returnsize = list.size();
        }
        System.out.println(returnsize);
        if (returnsize > 0) {
            //主数据记录
            JSONArray jsonlist = JsonUtil.fromObject(list);
            JSONObject maindata = jsonlist.getJSONObject(0);
            dataMap.put("maindata", maindata);

        }
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
            MANAGE_MODELEntity maindata = (MANAGE_MODELEntity) JsonUtil.toBean(jsonString, MANAGE_MODELEntity.class);
            if (null == maindata.getICODE() || maindata.getICODE().isEmpty()) {
                maindata.setICODE(UUID.randomUUID().toString());
//				if(null==maindata.getPARENTICODE()|| maindata.getPARENTICODE().isEmpty())
//					maindata.setPARENTICODE(UUID.randomUUID().toString());
                //公用字段
                //InitCreate(maindata);
                int returncount = manage_modelService.insert(maindata);
                dataMap.put("maindatauuid", maindata.getICODE());
                dataMap.put("savetype", "insert");
                dataMap.put("returncount", returncount);
            } else {
                //公用字段
                //InitModidy(maindata);
                int returncount = manage_modelService.update(maindata);
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
        int returncount = manage_modelService.delete(maindatauuid);

        dataMap.put("returncount", returncount);
        return SUCCESS;
    }

}
