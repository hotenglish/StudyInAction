package com.elson.chapter4.association.dao;

import com.elson.chapter4.association.pojo.StudentHealthFemale;

public interface StudentHealthFemaleDao {
    StudentHealthFemale findStudentHealthFemaleByStudentId(Integer id);
}
