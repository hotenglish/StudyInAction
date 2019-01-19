package com.manage.platform.dao;

import java.util.List;
import java.util.Map;

import com.manage.platform.entity.MANAGE_USER_ROLEEntity;

public interface IMANAGE_USER_ROLEDao {
	/**
	 * @author wangbo
	 * @despcription 新增
	 */
	public abstract int insert(MANAGE_USER_ROLEEntity entity);

	/**
	 * @param(USER_ROLEId)
	 * @author wangbo
	 * @despcription 更新
	 */
	public abstract int update(MANAGE_USER_ROLEEntity entity);

	/**
	 * @author wangbo
	 * @despcription 根据条件查询数据列表
	 */
	public abstract List<Map<String, Object>> findByCondition(String condition, int start, int count);

	/**
	 * @author wangbo
	 * @despcription 根据条件查询数据条数
	 */
	public abstract int countByCondition(String condition);
	
	/**
	 * @author wangbo
	 * @despcription 删除
	 */
	public abstract int delete(String uuid);

	public abstract int deleteByUsericode(String uSERICODE);

	

}