package com.elson.chapter9.main;

import com.elson.chapter9.mapper.BillMapper;
import com.elson.chapter9.pojo.Bill;
import com.elson.chapter9.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;

public class C9BillMain {
    public static void main(String args[]) throws Exception {
        SqlSession session = SqlSessionFactoryUtil.openSqlSession();
        BillMapper mapper=session.getMapper(BillMapper.class);
        Bill bill2017=mapper.getBill(2017,1L);
        System.out.println(bill2017);
        Bill bill2018=mapper.getBill(2018,2L);
        System.out.println(bill2018);
    }
}
