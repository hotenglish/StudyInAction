package com.manage.platform.service;

import com.manage.platform.entity.MANAGE_MODELEntity;

import java.util.*;

public interface IMANAGE_MODELService {
	
	public abstract int insert(MANAGE_MODELEntity entity);

	public abstract int update(MANAGE_MODELEntity entity);

	public abstract List<Map<String, Object>> findByCondition(String condition, int start, int count);
	
	public abstract List<Map<String, Object>> findGridByCondition(String condition);
	
	public abstract List<Map<String, Object>> findTreeByCondition(String condition);

	public abstract int countByCondition(String condition);

	public abstract int delete(String uuid);

	public abstract List<Map<String, Object>> findLgoinMenu(String icode,
			String string, String string2);

}