package com.manage.platform.service.impl;

import java.util.List;
import java.util.Map;

import com.manage.platform.dao.IMANAGE_USER_ROLEDao;
import com.manage.platform.entity.MANAGE_USER_ROLEEntity;
import com.manage.platform.service.IMANAGE_USER_ROLEService;

public class MANAGE_USER_ROLEServiceImpl implements IMANAGE_USER_ROLEService {

    private IMANAGE_USER_ROLEDao manage_USER_ROLEdao;

    public IMANAGE_USER_ROLEDao getManage_USER_ROLEdao() {
        return manage_USER_ROLEdao;
    }

    public void setManage_USER_ROLEdao(IMANAGE_USER_ROLEDao manage_USER_ROLEdao) {
        this.manage_USER_ROLEdao = manage_USER_ROLEdao;
    }

    public int insert(MANAGE_USER_ROLEEntity entity) {
        return manage_USER_ROLEdao.insert(entity);
    }

    public int update(MANAGE_USER_ROLEEntity entity) {
        return manage_USER_ROLEdao.update(entity);
    }

    public List<Map<String, Object>> findByCondition(String condition, int start, int count) {
        return manage_USER_ROLEdao.findByCondition(condition, start, count);
    }

    public int countByCondition(String condition) {
        return manage_USER_ROLEdao.countByCondition(condition);
    }

    public int delete(String uuid) {
        return manage_USER_ROLEdao.delete(uuid);
    }

    public void deleteByUsericode(String uSERICODE) {
        manage_USER_ROLEdao.deleteByUsericode(uSERICODE);
    }

}
