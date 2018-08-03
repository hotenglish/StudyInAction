package com.learn.chapter8.dao;

import com.learn.chapter8.pojo.FileBean;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDao {

    void insertFile(FileBean file);

}
