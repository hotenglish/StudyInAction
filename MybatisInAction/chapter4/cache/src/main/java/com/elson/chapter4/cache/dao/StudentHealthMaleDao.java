package com.elson.chapter4.cache.dao;

import com.elson.chapter4.cache.pojo.StudentHealthMale;

public interface StudentHealthMaleDao {
    StudentHealthMale findStudentHealthMaleByStudentId(Integer id);
}
