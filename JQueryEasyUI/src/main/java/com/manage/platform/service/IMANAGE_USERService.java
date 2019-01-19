package com.manage.platform.service;

import com.manage.platform.entity.MANAGE_USEREntity;

import java.util.*;

public interface IMANAGE_USERService {
	
	public abstract int insert(MANAGE_USEREntity entity);

	public abstract int update(MANAGE_USEREntity entity);

	public abstract List<Map<String, Object>> findByCondition(String condition, int start, int count);

	public abstract int countByCondition(String condition);

	public abstract int delete(String uuid);

	public abstract List<Map<String, Object>> findGridByCondition(String condition);
	public abstract List<Map<String, Object>> findByLOGINNAME(String condition,int start, int count);
	public abstract int countByLOGINNAME(String condition) ;
}