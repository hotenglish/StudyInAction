package com.elson.chapter4.cache.dao;

import com.elson.chapter4.cache.pojo.StudentLecture;

import java.util.List;

public interface StudentLectureDao {
    List<StudentLecture> findStudentLectureByStudentId(Integer id);
}
