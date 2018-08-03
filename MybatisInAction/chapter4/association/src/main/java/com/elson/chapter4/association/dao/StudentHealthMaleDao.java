package com.elson.chapter4.association.dao;

import com.elson.chapter4.association.pojo.StudentHealthMale;

public interface StudentHealthMaleDao {
    StudentHealthMale findStudentHealthMaleByStudentId(Integer id);
}
