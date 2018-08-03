package com.elson.chapter4.cache.dao;

import com.elson.chapter4.cache.pojo.StudentSelfCard;

public interface StudentSelfCardDao {
    StudentSelfCard findStudentSelfCardByStudentId(Integer studentId);
}
