package com.manage.report.action;

import com.fore.util.JsonUtil;
import com.manage.platform.action.ActionBase;
import com.manage.platform.entity.MANAGE_AREAEntity;
import com.manage.platform.entity.MANAGE_USEREntity;
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
import java.util.UUID;

public class SendCityAction extends ActionBase implements Action {

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
        MANAGE_USEREntity userdata = (MANAGE_USEREntity) session.getAttribute("user");

        MANAGE_AREAEntity usercode = (MANAGE_AREAEntity) session.getAttribute("area");

//		System.out.println("user==="+userdata.getNAME());
//		System.out.println("user==="+userdata.getAREAICODE());
//		System.out.println("user==="+userdata.getLOGINNAME());
//		System.out.println("user==="+userdata.getNO());
//		System.out.println("user==="+userdata.getSTOPFLAG());
//
//		System.out.println("usercode==="+usercode.getNO());
//		System.out.println("usercode==="+usercode.getCUSTOMNO());
//		System.out.println("usercode==="+usercode.getNAME());
        if (usercode.getNO().length() == 3) {
            System.out.println("这是市区123");

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
                System.out.println("dateStart1===" + dateStart);
                String dateEnd = obj.containsKey("dateEnd") ? obj.getString("dateEnd") : "";
                System.out.println("dateEnd2===" + dateEnd);
                String tbUsername = obj.containsKey("tbUsername") ? obj.getString("tbUsername") : "";

                if (null != tbUsername && tbUsername.length() > 0) {
                    sbwhere.append(" FULLNAME like'%" + tbUsername + "%' ");
                }
                if ((null != dateStart && dateStart.length() > 0) && (null != dateEnd && dateEnd.length() > 0)) {
                    sbwhere.append(" where to_char(SENDDATE,'yyyymmdd')>=" + "'" + dateStart + "'" + " and to_char(SENDDATE,'yyyymmdd')<=" + "'" + dateEnd + "'");
                }
            }

            //查询分页数据sql语句（特别注意，查询字段中一定要有这个字段：）
            StringBuffer sbfind = new StringBuffer();
            //sbfind.append(" SELECT TEST4.*, row_number() OVER(ORDER BY null) AS row_number FROM TEST4 ");
            System.out.println("usercode.getNO()===" + usercode.getNO());
            System.out.println("usercode.getName()===" + usercode.getNAME());

            //sbfind.append("select chgmon,service_id,os,service_name,total_bytes,city_name,belong_area,rownum as row_number from T_AREADETAIL where stat = '1'");

            sbfind.append("SELECT CARDDATE,TEL,SOURCE,BILL_DATE,AREACODE,CONTACTTEL,CON_CARRIER,CONTACTNAME," +
                    "CONTACTADD,RESERVEDATE,MSISDN,PAGESOURCE,REGUA,TOTALBYTES,USED_BYTES,CONTACTUA,TELUA," +
                    "CONTACTIMEI,TELIMEI,PAYDATE,PAYTOTAL,IS_ALIPAY,IS_TENCENT,IS_BANK,IS_SOURCE," +
                    "ROWNUM AS ROW_NUMBER FROM T_CARDREPORT WHERE PAYDATE IS NOT NULL AND AREACODE=" +
                    "'" + usercode.getNAME() + "'" + sbwhere);

            if (sbwhere.length() > 0) {
                sbfind.append(" where " + sbwhere.toString());
            }

            //查询总条数的sql语句
            StringBuffer sbcount = new StringBuffer();
            //sbcount.append(" SELECT count(1) FROM TEST4 ");
            //sbcount.append(" SELECT count(1) FROM (SELECT CARDDATE,TEL,SOURCE,AREACODE,CON_CARRIER,CONTACTNAME,CONTACTADD FROM T_CARDREPORT"+sbwhere+")");

            sbcount.append("SELECT count(1) FROM (CARDDATE,TEL,SOURCE,BILL_DATE,AREACODE,CONTACTTEL,CON_CARRIER," +
                    "CONTACTNAME,CONTACTADD,RESERVEDATE,MSISDN,PAGESOURCE,REGUA,TOTALBYTES,USED_BYTES,CONTACTUA," +
                    "TELUA,CONTACTIMEI,TELIMEI,PAYDATE,PAYTOTAL,IS_ALIPAY,IS_TENCENT,IS_BANK,IS_SOURCE " +
                    "FROM T_CARDREPORT WHERE PAYDATE IS NOT NULL AND AREACODE=" +
                    "'" + usercode.getNAME() + "'" + sbwhere + ")");
            if (sbwhere.length() > 0) {
                sbcount.append(" where " + sbwhere.toString());
            }

            // 查询数据库 查询数据
            //System.out.println("sbfind==="+sbfind);

            List<Map<String, Object>> list = imanage_reportdao.findData(sbfind, start, pageCount);
            JSONArray jsonlist = JsonUtil.fromObject(list);
            System.out.println("jsonlist===" + jsonlist.toString());
            dataMap.put("rows", jsonlist);

            System.out.println("dataMap===" + dataMap);
            System.out.println("sbcount===" + sbcount);

            int count = imanage_reportdao.count(sbcount);
            dataMap.put("total", count);

            if (exportflag != null) {
                String srcDir = request.getRealPath("/");
                UUID uuid = UUID.randomUUID();
                String path = srcDir + "/temp/" + uuid + ".csv";
                List<Map<String, Object>> listCount = imanage_reportdao.findCount(sbfind);
                String flag = imanage_reportdao.export(listCount, path, uuid);

                dataMap.put("rows", flag);
                logger.info(dataMap.toString());

                //System.out.println("dataMap==="+dataMap);
                // 清空查询条件
                exportflag = null;
                condition = null;
            }
        }

        if (usercode.getNO().length() > 3) {
            System.out.println("这是县城");


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
                String tbUsername = obj.containsKey("tbUsername") ? obj.getString("tbUsername") : "";

                if (null != tbUsername && tbUsername.length() > 0) {
                    sbwhere.append(" FULLNAME like'%" + tbUsername + "%' ");
                }
            }

            //查询分页的sql语句（特别注意，查询字段中一定要有这个字段：）
            StringBuffer sbfind = new StringBuffer();

            sbfind.append("select chgmon,service_id,os,service_name,total_bytes," +
                    "city_name,belong_area,rownum as row_number from T_AREADETAIL" +
                    " where stat = '1' and belong_code =" + "'" + usercode.getNO() + "'");

            if (sbwhere.length() > 0) {
                sbfind.append(" where " + sbwhere.toString());
            }

            //查询总条数的sql语句
            StringBuffer sbcount = new StringBuffer();
            sbcount.append("SELECT count(1) FROM (select chgmon,service_id,os,service_name,total_bytes,city_name," +
                    "belong_area from T_AREADETAIL where stat = '1' and belong_code =" + "'" + usercode.getNO() + "')");

            if (sbwhere.length() > 0) {
                sbcount.append(" where " + sbwhere.toString());
            }

            // 查询数据库
            List<Map<String, Object>> list = imanage_reportdao.findData(sbfind, start, pageCount);
            JSONArray jsonlist = JsonUtil.fromObject(list);
            //System.out.println("jsonlist==="+jsonlist.toString());
            dataMap.put("rows", jsonlist);

            System.out.println("sbcount=" + sbcount);
            int count = imanage_reportdao.count(sbcount);
            dataMap.put("total", count);

            if (exportflag != null) {
                String srcDir = request.getRealPath("/");
                UUID uuid = UUID.randomUUID();
                String path = srcDir + "/temp/" + uuid + ".csv";
                List<Map<String, Object>> listCount = imanage_reportdao.findCount(sbfind);
                String flag = imanage_reportdao.export(listCount, path, uuid);

                dataMap.put("rows", flag);
                logger.info(dataMap.toString());

                //System.out.println("dataMap==="+dataMap);
                // 清空查询条件
                exportflag = null;
                condition = null;
            }

        }

        //销售总部
        if (usercode.getNAME().equals("销售总部")) {
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

            // 查询条件
            StringBuffer sbwhere = new StringBuffer();
            if (null != condition && !condition.isEmpty()) {
                JSONObject obj = JSONObject.fromObject(condition);
                String dateStart = obj.containsKey("dateStart") ? obj.getString("dateStart") : "";
                String dateEnd = obj.containsKey("dateEnd") ? obj.getString("dateEnd") : "";
                dateStart = dateStart.replace("-", "");
                dateEnd = dateEnd.replace("-", "");
                String tbUsername = obj.containsKey("tbUsername") ? obj.getString("tbUsername") : "";

                if (null != tbUsername && tbUsername.length() > 0) {
                    sbwhere.append(" FULLNAME like'%" + tbUsername + "%' ");
                }

                if ((null != dateStart && dateStart.length() > 0) && (null != dateEnd && dateEnd.length() > 0)) {
                    sbwhere.append(" where to_char(SENDDATE,'yyyymmdd')>=" + "'" + dateStart + "'" + " and to_char(SENDDATE,'yyyymmdd')<=" + "'" + dateEnd + "'");
                }
            }

            // 查询分页的sql语句
            StringBuffer sbFind = new StringBuffer();
            sbFind.append(
                    "SELECT CITY,\n" +
                            "       GOODS,\n" +
                            "       AMOUNT,\n" +
                            "       RECEIVER,\n" +
                            "       TAKEDATE,\n" +
                            "       SENDDATE,\n" +
                            "       REMARK,\n" +
                            "       ROWNUM AS ROW_NUMBER\n" +
                            "  FROM GOODS_SENDCOUNT"
                            + sbwhere);

            // 查询总条数的sql语句
            StringBuffer sbCount = new StringBuffer();
            sbCount.append(
                    "SELECT count(1)\n" +
                            "  FROM (SELECT *\n" +
                            "          FROM GOODS_SENDCOUNT"
                            + sbwhere + ")");

            //查询列表
            List<Map<String, Object>> list = imanage_reportdao.findData(sbFind, start, pageCount);
            JSONArray jsonlist = JsonUtil.fromObject(list);
            int count = imanage_reportdao.count(sbCount);
            dataMap.put("rows", jsonlist);
            dataMap.put("total", count);

            if (exportflag != null && exportflag.equals("excel")) {
                String srcDir = request.getRealPath("/");
                UUID uuid = UUID.randomUUID();
                String path = srcDir + "temp/" + uuid + ".xls";
                String flag = imanage_reportdao.exportExcel(list, path, uuid);
                dataMap.put("rows", flag);
                logger.info(dataMap.toString());
                exportflag = null;
                condition = null;
            }

            if (exportflag != null && exportflag.equals("csv")) {
                String srcDir = request.getRealPath("/");
                UUID uuid = UUID.randomUUID();
                String path = srcDir + "temp/" + uuid + ".csv";
                String flag = imanage_reportdao.exportCsv(list, path, uuid);
                dataMap.put("rows", flag);
                logger.info(dataMap.toString());
                exportflag = null;
                condition = null;
            }

        }

        // 清空查询条件
        exportflag = null;
        condition = null;

        return SUCCESS;
    }
}
