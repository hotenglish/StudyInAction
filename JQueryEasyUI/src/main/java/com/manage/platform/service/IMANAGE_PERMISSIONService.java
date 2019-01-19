package com.manage.platform.service;

import com.manage.platform.entity.MANAGE_PERMISSIONEntity;

import java.util.*;

public interface IMANAGE_PERMISSIONService {
	
	public abstract int insert(MANAGE_PERMISSIONEntity entity);

	public abstract int update(MANAGE_PERMISSIONEntity entity);

	public abstract List<Map<String, Object>> findByCondition(String condition, int start, int count);

	public abstract int countByCondition(String condition);

	public abstract int delete(String uuid);

	public abstract void deleteByRoleicode(String rOLDICODE);

	
}