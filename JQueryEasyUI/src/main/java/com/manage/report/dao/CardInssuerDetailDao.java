package com.manage.report.dao;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.manage.platform.entity.CardInssuerDetail;

public interface CardInssuerDetailDao {
	/**
	 * @author wangbo
	 * @despcription 根据条件查询数据列表
	 */
	public abstract List<Map<String, Object>> findByCondition(String condition);
	public List<CardInssuerDetail> findByConditionList(String condition);
	//service.exportEmployeeTransitionData(request,response,form.getRevenueDate());
	public String export(List<CardInssuerDetail> novels,String fileName,UUID uuid);
    public InputStream getDownloadFile();

}
