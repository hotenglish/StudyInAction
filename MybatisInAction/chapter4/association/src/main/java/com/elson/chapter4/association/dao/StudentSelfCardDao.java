package com.elson.chapter4.association.dao;

import com.elson.chapter4.association.pojo.StudentSelfCard;

public interface StudentSelfCardDao {
    StudentSelfCard findStudentSelfCardByStudentId(Integer studentId);
}
