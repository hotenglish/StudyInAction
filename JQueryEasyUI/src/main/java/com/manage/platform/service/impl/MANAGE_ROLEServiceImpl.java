package com.manage.platform.service.impl;

import java.util.List;
import java.util.Map;

import com.manage.platform.dao.IMANAGE_ROLEDao;
import com.manage.platform.entity.MANAGE_ROLEEntity;
import com.manage.platform.service.IMANAGE_ROLEService;

public class MANAGE_ROLEServiceImpl implements IMANAGE_ROLEService{

	private IMANAGE_ROLEDao manage_ROLEdao;
			
	public IMANAGE_ROLEDao getManage_ROLEdao() {
		return manage_ROLEdao;
	}

	public void setManage_ROLEdao(IMANAGE_ROLEDao manage_ROLEdao) {
		this.manage_ROLEdao = manage_ROLEdao;
	}

	public int insert(MANAGE_ROLEEntity entity) {
		return manage_ROLEdao.insert(entity);
	}

	public int update(MANAGE_ROLEEntity entity) {
		return manage_ROLEdao.update(entity);
	}

	public List<Map<String, Object>> findByCondition(String condition,int start, int count) {
		return manage_ROLEdao.findByCondition(condition, start, count);
	}

	public int countByCondition(String condition) {
		return manage_ROLEdao.countByCondition(condition);
	}

	public int delete(String uuid) {
		return manage_ROLEdao.delete(uuid);
	}


}
