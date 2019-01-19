package com.manage.report.action;

import com.manage.platform.action.ActionBase;
import com.manage.report.dao.ITestStrutsDao;
import com.opensymphony.xwork2.Action;

public class TestStrutsAction  extends ActionBase implements Action {

    private ITestStrutsDao testStrutsDao;

    public ITestStrutsDao getTestStrutsDao() {
        return testStrutsDao;
    }

    public void setTestStrutsDao(ITestStrutsDao testStrutsDao) {
        this.testStrutsDao = testStrutsDao;
    }

    @Override
    public String execute() throws Exception {
        return null;
    }
}
