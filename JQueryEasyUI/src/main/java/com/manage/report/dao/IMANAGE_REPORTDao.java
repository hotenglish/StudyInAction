package com.manage.report.dao;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.manage.data.bean.Send;

public interface IMANAGE_REPORTDao {
	
	/**
	 * @author wangbo
	 * @despcription 根据条件查询数据列表
	 */
	public abstract List<Map<String, Object>> findData(StringBuffer sql, int start, int count);
	public abstract List<Map<String, Object>> findCount(StringBuffer sql);
	public abstract List<Map<String, Object>> findDataResult(String condition, int start, int count);
	public abstract boolean addGoods();
	public abstract boolean addGoods(Send sendEntity);
	/**
	 * @author wangbo
	 * @despcription 根据条件查询数据条数
	 */
	public abstract int count(StringBuffer sql);
	
	/**
	 * @author wangbo
	 * @despcription 导出功能
	 */
	public abstract String export(List<Map<String, Object>> list, String fileName,
			UUID uuid);
	
	public abstract String exportExcel(List<Map<String, Object>> list, String fileName,
			UUID uuid);
	
	public abstract String exportCsv(List<Map<String, Object>> list, String fileName,
			UUID uuid);
}