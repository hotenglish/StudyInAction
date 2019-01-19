package com.manage.platform.service.impl;

import java.util.List;
import java.util.Map;

import com.manage.platform.dao.IMANAGE_USERDao;
import com.manage.platform.entity.MANAGE_USEREntity;
import com.manage.platform.service.IMANAGE_USERService;

public class MANAGE_USERServiceImpl implements IMANAGE_USERService{

	private IMANAGE_USERDao manage_userdao;
			
	public IMANAGE_USERDao getManage_userdao() {
		return manage_userdao;
	}

	public void setManage_userdao(IMANAGE_USERDao manage_userdao) {
		this.manage_userdao = manage_userdao;
	}

	public int insert(MANAGE_USEREntity entity) {
		return manage_userdao.insert(entity);
	}

	public int update(MANAGE_USEREntity entity) {
		return manage_userdao.update(entity);
	}

	public List<Map<String, Object>> findByCondition(String condition,int start, int count) {
		return manage_userdao.findByCondition(condition, start, count);
	}

	public int countByCondition(String condition) {
		return manage_userdao.countByCondition(condition);
	}

	public int delete(String uuid) {
		return manage_userdao.delete(uuid);
	}

	public List<Map<String, Object>> findGridByCondition(String condition) {
		return manage_userdao.findGridByCondition(condition);
	}

	public List<Map<String, Object>> findByLOGINNAME(String condition,
			int start, int count) {
		// TODO Auto-generated method stub
		return manage_userdao.findByLOGINNAME(condition, start, count);
	}

	public int countByLOGINNAME(String condition) {
		// TODO Auto-generated method stub
		return manage_userdao.countByLOGINNAME(condition);
	}

}
