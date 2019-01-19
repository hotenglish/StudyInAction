package com.manage.platform.action;

import net.sf.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class ActionBase {

    protected static final Logger logger= LoggerFactory.getLogger("interfaceLogger");

    protected String page;
    protected String rows;
    protected Map dataMap = new HashMap();
    protected String condition;
    protected String maindatauuid;
    protected String maindata;
    protected JSONArray jsonarr;
    protected String exportflag;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getRows() {
        return rows;
    }

    public void setRows(String rows) {
        this.rows = rows;
    }

    public Map getDataMap() {
        return dataMap;
    }

    public void setDataMap(Map dataMap) {
        this.dataMap = dataMap;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getMaindatauuid() {
        return maindatauuid;
    }

    public void setMaindatauuid(String maindatauuid) {
        this.maindatauuid = maindatauuid;
    }

    public String getMaindata() {
        return maindata;
    }

    public void setMaindata(String maindata) {
        this.maindata = maindata;
    }

    public JSONArray getJsonarr() {
        return jsonarr;
    }

    public void setJsonarr(JSONArray jsonarr) {
        this.jsonarr = jsonarr;
    }

    public String getExportflag() {
        return exportflag;
    }

    public void setExportflag(String exportflag) {
        this.exportflag = exportflag;
    }
}
