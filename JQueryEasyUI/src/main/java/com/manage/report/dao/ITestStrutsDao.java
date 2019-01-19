package com.manage.report.dao;

import java.util.List;
import java.util.Map;

public interface ITestStrutsDao {

	public abstract List<Map<String, Object>> findByCondition(String condition);

}
