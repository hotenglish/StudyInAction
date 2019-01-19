package com.manage.platform.service.impl;

import com.manage.platform.dao.IMANAGE_MODELDao;
import com.manage.platform.entity.MANAGE_MODELEntity;
import com.manage.platform.service.IMANAGE_MODELService;

import java.util.List;
import java.util.Map;

public class MANAGE_MODELServiceImpl implements IMANAGE_MODELService {

    private IMANAGE_MODELDao MANAGE_MODELdao;

    public IMANAGE_MODELDao getMANAGE_MODELdao() {
        return MANAGE_MODELdao;
    }

    public void setMANAGE_MODELdao(IMANAGE_MODELDao MANAGE_MODELdao) {
        this.MANAGE_MODELdao = MANAGE_MODELdao;
    }

    @Override
    public int insert(MANAGE_MODELEntity entity) {
        return MANAGE_MODELdao.insert(entity);
    }

    @Override
    public int update(MANAGE_MODELEntity entity) {
        return MANAGE_MODELdao.update(entity);
    }

    @Override
    public List<Map<String, Object>> findByCondition(String condition, int start, int count) {
        return MANAGE_MODELdao.findByCondition(condition, start, count);
    }

    @Override
    public List<Map<String, Object>> findGridByCondition(String condition) {
        return MANAGE_MODELdao.findGridByCondition(condition);
    }

    @Override
    public List<Map<String, Object>> findTreeByCondition(String condition) {
        return MANAGE_MODELdao.findTreeByCondition(condition);
    }

    @Override
    public int countByCondition(String condition) {
        return MANAGE_MODELdao.countByCondition(condition);
    }

    @Override
    public int delete(String uuid) {
        return MANAGE_MODELdao.delete(uuid);
    }

    @Override
    public List<Map<String, Object>> findLgoinMenu(String icode, String string, String string2) {
        return MANAGE_MODELdao.findLgoinMenu(icode, string, string2);
    }
}
