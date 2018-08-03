package com.elson.chapter4.cache.dao;

import com.elson.chapter4.cache.pojo.Student;

import java.util.List;

public interface StudentDao {
    Student getStudent(Integer id);

    List<Student> findAllStudent();
}
