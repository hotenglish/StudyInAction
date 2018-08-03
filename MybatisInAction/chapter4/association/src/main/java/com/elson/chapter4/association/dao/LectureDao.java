package com.elson.chapter4.association.dao;

import com.elson.chapter4.association.pojo.Lecture;

import java.util.List;

public interface LectureDao {
    List<Lecture>  getLecture(Integer id);
}
