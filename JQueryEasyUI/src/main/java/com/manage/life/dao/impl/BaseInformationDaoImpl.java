package com.manage.life.dao.impl;

import com.manage.life.dao.BaseInformationDao;
import com.manage.platform.dao.impl.DaoImplBase;

import java.util.Map;

public class BaseInformationDaoImpl extends DaoImplBase implements BaseInformationDao {

    @Override
    public String baseInformationInsert(Map<String, String> baseInformation) {

        final String name = baseInformation.get("name");
        final String sex = baseInformation.get("sex");
        final String birth = baseInformation.get("birth");
        final String mobile = baseInformation.get("mobile");
        final String communication = baseInformation.get("communication");
        final String message = baseInformation.get("message");
        final String hobby = baseInformation.get("hobby");
        final String remark = baseInformation.get("remark");
        final String nationality = baseInformation.get("nationality");

        System.out.println("ffffffff1234==="+name);
        String sql = "insert into life_baseinformation(id,name,sex,birth,mobile,communication,message,hobby,remark,nationality)" +
                " VALUES(:id,:name,:sex,:birth,:mobile,:communication,:message,:hobby,:remark,:nationality)";
        namedjdbcTemplate.update(sql, baseInformation);
        return null;

    }

}
