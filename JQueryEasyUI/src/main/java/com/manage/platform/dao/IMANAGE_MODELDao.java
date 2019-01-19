package com.manage.platform.dao;

import java.util.List;
import java.util.Map;

import com.manage.platform.entity.MANAGE_MODELEntity;

public interface IMANAGE_MODELDao {
	/**
	 * @author wangbo
	 * @despcription 新增
	 */
	public abstract int insert(MANAGE_MODELEntity entity);

	/**
	 * @param(userId)
	 * @author wangbo
	 * @despcription 更新
	 */
	public abstract int update(MANAGE_MODELEntity entity);

	/**
	 * @author wangbo
	 * @despcription 根据条件查询数据列表
	 */
	public abstract List<Map<String, Object>> findByCondition(String condition, int start, int count);
	
	public abstract List<Map<String, Object>> findGridByCondition(String condition);
	
	public abstract List<Map<String, Object>> findTreeByCondition(String condition);

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

	public abstract List<Map<String, Object>> findLgoinMenu(String icode,
			String string, String string2);

	
	

}