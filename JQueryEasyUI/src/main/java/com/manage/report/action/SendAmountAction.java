package com.manage.report.action;

import com.manage.platform.action.ActionBase;
import com.manage.platform.dao.impl.DaoImplBase;
import com.manage.report.dao.IMANAGE_REPORTDao;
import com.manage.report.dao.ITestStrutsDao;
import com.opensymphony.xwork2.Action;

public class SendAmountAction extends ActionBase implements Action {

    private IMANAGE_REPORTDao imanage_reportdao;

    public IMANAGE_REPORTDao getImanage_reportdao() {
        return imanage_reportdao;
    }

    public void setImanage_reportdao(IMANAGE_REPORTDao imanage_reportdao) {
        this.imanage_reportdao = imanage_reportdao;
    }

    @Override
    public String execute() throws Exception {
        return null;
    }
}
