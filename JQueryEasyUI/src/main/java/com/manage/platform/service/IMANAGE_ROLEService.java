package com.manage.platform.service;

import com.manage.platform.entity.MANAGE_ROLEEntity;

import java.util.*;

public interface IMANAGE_ROLEService {
	
	public abstract int insert(MANAGE_ROLEEntity entity);

	public abstract int update(MANAGE_ROLEEntity entity);

	public abstract List<Map<String, Object>> findByCondition(String condition, int start, int count);

	public abstract int countByCondition(String condition);

	public abstract int delete(String uuid);

	
}