package com.elson.chapter4.association.pojo;

import java.util.List;

public class Student {
    private Integer id;
    private String cnName;
    private SexEnum sex;
    private String selfNo;
    private String note;
    private StudentSelfCard studentSelfCard;
    private List<StudentLecture> studentLectureList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public SexEnum getSex() {
        return sex;
    }

    public void setSex(SexEnum sex) {
        this.sex = sex;
    }

    public String getSelfNo() {
        return selfNo;
    }

    public void setSelfNo(String selfNo) {
        this.selfNo = selfNo;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public StudentSelfCard getStudentSelfCard() {
        return studentSelfCard;
    }

    public void setStudentSelfCard(StudentSelfCard studentSelfCard) {
        this.studentSelfCard = studentSelfCard;
    }

    public List<StudentLecture> getStudentLectureList() {
        return studentLectureList;
    }

    public void setStudentLectureList(List<StudentLecture> studentLectureList) {
        this.studentLectureList = studentLectureList;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", cnName='" + cnName + '\'' +
                ", sex=" + sex +
                ", selfNo='" + selfNo + '\'' +
                ", note='" + note + '\'' +
                ", studentSelfCard=" + studentSelfCard +
                ", studentLectureList=" + studentLectureList +
                '}';
    }
}
