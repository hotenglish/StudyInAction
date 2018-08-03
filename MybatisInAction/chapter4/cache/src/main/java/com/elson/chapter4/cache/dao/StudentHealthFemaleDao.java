package com.elson.chapter4.cache.dao;

import com.elson.chapter4.cache.pojo.StudentHealthFemale;

public interface StudentHealthFemaleDao {
    StudentHealthFemale findStudentHealthFemaleByStudentId(Integer id);
}
