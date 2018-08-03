package com.elson.chapter4.association.dao;

import com.elson.chapter4.association.pojo.StudentLecture;

import java.util.List;

public interface StudentLectureDao {
    List<StudentLecture> findStudentLectureByStudentId(Integer id);
}
