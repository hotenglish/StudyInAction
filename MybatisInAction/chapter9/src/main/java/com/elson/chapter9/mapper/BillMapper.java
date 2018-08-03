package com.elson.chapter9.mapper;

import com.elson.chapter9.pojo.Bill;
import org.apache.ibatis.annotations.Param;

public interface BillMapper {

    Bill getBill(@Param("year") int year, @Param("id") Long id);

}
