package com.elson.chapter9.main;

import com.elson.chapter9.mapper.ProcedureMapper;
import com.elson.chapter9.pojo.ProcedurePojo;
import com.elson.chapter9.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;

import java.text.SimpleDateFormat;

public class C9ProcedureMain {
    public static void main(String args[]) {
        SqlSession session = SqlSessionFactoryUtil.openSqlSession();
        ProcedureMapper procedureMapper = session.getMapper(ProcedureMapper.class);
        int result = 0;
        ProcedurePojo pojo = new ProcedurePojo();
        pojo.setRoleName("me11");
        procedureMapper.count(pojo);
        System.err.println(pojo.getRoleName() + "\t" + pojo.getResult() + "\t");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        System.err.println(df.format(pojo.getExecDate()));
    }
}
