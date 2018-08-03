package com.elson.chapter4.cache.dao;

import com.elson.chapter4.cache.pojo.Lecture;

import java.util.List;

public interface LectureDao {
    List<Lecture>  getLecture(Integer id);
}
