package com.manage.data.action;

import com.fore.util.JsonUtil;
import com.manage.data.bean.Send;
import com.manage.platform.action.ActionBase;
import com.manage.platform.entity.MANAGE_AREAEntity;
import com.manage.report.dao.IMANAGE_REPORTDao;
import com.opensymphony.xwork2.Action;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

public class AddGoodsAction extends ActionBase implements Action {

    private IMANAGE_REPORTDao imanage_reportdao;

    public IMANAGE_REPORTDao getImanage_reportdao() {
        return imanage_reportdao;
    }

    public void setImanage_reportdao(IMANAGE_REPORTDao imanage_reportdao) {
        this.imanage_reportdao = imanage_reportdao;
    }

    @Override
    public String execute() throws Exception {

        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = (request).getSession(true);
        MANAGE_AREAEntity usercode = (MANAGE_AREAEntity) session.getAttribute("area");
        //其他权限预留方法
        if (usercode.getNAME().equals("成都")) {
        }

        //销售总部
        if (usercode.getNAME().equals("销售总部")) {


            String CITY = request.getParameter("CITY");
            String GOODS = request.getParameter("GOODS");
            String AMOUNT = request.getParameter("AMOUNT");
            String RECEIVER = request.getParameter("RECEIVER");
            String SENDDATE = request.getParameter("SENDDATE");
            String TAKEDATE = request.getParameter("TAKEDATE");
            String REMARK = request.getParameter("REMARK");
            String flag = request.getParameter("flag");

            Send send = new Send();
            send.setCity(CITY);
            send.setGoods(GOODS);
            send.setAmount(AMOUNT);
            send.setReceiver(RECEIVER);
            send.setSenddate(SENDDATE);
            send.setTakedate(TAKEDATE);
            send.setRemark(REMARK);

            if (flag != null && flag.equals("add")) {
                boolean addFlag = imanage_reportdao.addGoods(send);
                dataMap.put("total", 0);
                dataMap.put("rows", "sdfsdf");
                return null;
            } else {

            }
            dataMap.clear();
            // 当前页
            int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);
            // 每页显示条数
            int pageCount = Integer.parseInt((rows == null || rows == "0") ? "20" : rows);
            int start = (intPage - 1) * pageCount + 1;
            // 界面输入的参数
            if (null != condition && condition.length() > 0) {
                try {
                    condition = java.net.URLDecoder.decode(condition, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            //查询条件
            StringBuffer sbwhere = new StringBuffer();
            if (null != condition && !condition.isEmpty()) {
                JSONObject obj = JSONObject.fromObject(condition);
                String dateStart = obj.containsKey("dateStart") ? obj.getString("dateStart") : "";
                String dateEnd = obj.containsKey("dateEnd") ? obj.getString("dateEnd") : "";
                dateStart = dateStart.replace("-", "");
                dateEnd = dateEnd.replace("-", "");
                if ((null != dateStart && dateStart.length() > 0) && (null != dateEnd && dateEnd.length() > 0)) {
                    sbwhere.append(" WHERE to_char(SENDDATE,'yyyymmdd')>=" + "'" + dateStart +
                            "'" + " and to_char(TAKEDATE,'yyyymmdd')<=" + "'" + dateEnd + "'");
                }
            }

            //查询数据的SQL语句
            StringBuffer sbfind = new StringBuffer();
            sbfind.append(
                    "SELECT CITY,\n" +
                            "       GOODS,\n" +
                            "       AMOUNT,\n" +
                            "       RECEIVER,\n" +
                            "       SENDDATE,\n" +
                            "       TAKEDATE,\n" +
                            "       REMARK,\n" +
                            "       ROWNUM AS ROW_NUMBER\n" +
                            "  FROM GOODS_SENDCOUNT"
                            + sbwhere);

            //查询总条数的SQL语句
            StringBuffer sbcount = new StringBuffer();
            sbcount.append(
                    "SELECT count(1)\n" +
                            "  FROM (SELECT *\n" +
                            "          FROM GOODS_SENDCOUNT"
                            + sbwhere + ")");

            //增加商品
            //boolean flag = imanage_reportdao.addGoods();
            //查询列表
            List<Map<String, Object>> list = imanage_reportdao.findData(sbfind, start, pageCount);
            //改造
            //List<Map<String, Object>> listResult = imanage_reportdao.findDataResult(condition,start, pageCount);
            JSONArray jsonList = JsonUtil.fromObject(list);

            int count = imanage_reportdao.count(sbcount);
            dataMap.put("rows", jsonList);
            dataMap.put("total", count);
            //数据导出预留方法
            if (exportflag != null) {
            }

        }
        //清空查询条件
        exportflag = null;
        condition = null;
        return SUCCESS;
    }
}
