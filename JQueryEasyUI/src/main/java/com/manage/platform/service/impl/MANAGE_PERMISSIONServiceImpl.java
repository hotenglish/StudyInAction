package com.manage.platform.service.impl;

import java.util.List;
import java.util.Map;

import com.manage.platform.dao.IMANAGE_PERMISSIONDao;
import com.manage.platform.entity.MANAGE_PERMISSIONEntity;
import com.manage.platform.service.IMANAGE_PERMISSIONService;

public class MANAGE_PERMISSIONServiceImpl implements IMANAGE_PERMISSIONService{

	private IMANAGE_PERMISSIONDao manage_PERMISSIONdao;
			
	public IMANAGE_PERMISSIONDao getManage_PERMISSIONdao() {
		return manage_PERMISSIONdao;
	}

	public void setManage_PERMISSIONdao(IMANAGE_PERMISSIONDao manage_PERMISSIONdao) {
		this.manage_PERMISSIONdao = manage_PERMISSIONdao;
	}

	public int insert(MANAGE_PERMISSIONEntity entity) {
		return manage_PERMISSIONdao.insert(entity);
	}

	public int update(MANAGE_PERMISSIONEntity entity) {
		return manage_PERMISSIONdao.update(entity);
	}

	public List<Map<String, Object>> findByCondition(String condition,int start, int count) {
		return manage_PERMISSIONdao.findByCondition(condition, start, count);
	}

	public int countByCondition(String condition) {
		return manage_PERMISSIONdao.countByCondition(condition);
	}

	public int delete(String uuid) {
		return manage_PERMISSIONdao.delete(uuid);
	}

	public void deleteByRoleicode(String rOLDICODE) {
		 manage_PERMISSIONdao.deleteByRoleicode(rOLDICODE);
	}

}
