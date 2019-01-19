package com.manage.platform.service.impl;

import com.manage.platform.dao.IMANAGE_AREADao;
import com.manage.platform.entity.MANAGE_AREAEntity;
import com.manage.platform.service.IMANAGE_AREAService;

import java.util.List;
import java.util.Map;

public class MANAGE_AREAServiceImpl implements IMANAGE_AREAService {

    private IMANAGE_AREADao MANAGE_AREAdao;

    public IMANAGE_AREADao getMANAGE_AREAdao() {
        return MANAGE_AREAdao;
    }

    public void setMANAGE_AREAdao(IMANAGE_AREADao MANAGE_AREAdao) {
        this.MANAGE_AREAdao = MANAGE_AREAdao;
    }

    @Override
    public int insert(MANAGE_AREAEntity entity) {
        return MANAGE_AREAdao.insert(entity);
    }

    @Override
    public int update(MANAGE_AREAEntity entity) {
        return MANAGE_AREAdao.update(entity);
    }

    @Override
    public List<Map<String, Object>> findByCondition(String condition, int start, int count) {
        return MANAGE_AREAdao.findByCondition(condition, start, count);
    }

    @Override
    public List<Map<String, Object>> findGridByCondition(String condition) {
        return MANAGE_AREAdao.findGridByCondition(condition);
    }

    @Override
    public List<Map<String, Object>> findTreeByCondition(String condition) {
        return MANAGE_AREAdao.findTreeByCondition(condition);
    }

    @Override
    public int countByCondition(String condition) {
        return MANAGE_AREAdao.countByCondition(condition);
    }

    @Override
    public int delete(String uuid) {
        return MANAGE_AREAdao.delete(uuid);
    }

}
