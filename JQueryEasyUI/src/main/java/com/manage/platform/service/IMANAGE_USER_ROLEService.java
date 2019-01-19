package com.manage.platform.service;

import com.manage.platform.entity.MANAGE_USER_ROLEEntity;

import java.util.*;

public interface IMANAGE_USER_ROLEService {
	
	public abstract int insert(MANAGE_USER_ROLEEntity entity);

	public abstract int update(MANAGE_USER_ROLEEntity entity);

	public abstract List<Map<String, Object>> findByCondition(String condition, int start, int count);

	public abstract int countByCondition(String condition);

	public abstract int delete(String uuid);

	public abstract void deleteByUsericode(String uSERICODE);

}