package com.elson.chapter4.association.pojo;

public class StudentHealthMale {
    private Integer id;
    private String student_id;
    private String check_date;
    private String heart;
    private String liver;
    private String spleen;
    private String lung;
    private String kidney;
    private String prostate;
    private String note;

    @Override
    public String toString() {
        return "StudentHealthMale{" +
                "id=" + id +
                ", student_id='" + student_id + '\'' +
                ", check_date='" + check_date + '\'' +
                ", heart='" + heart + '\'' +
                ", liver='" + liver + '\'' +
                ", spleen='" + spleen + '\'' +
                ", lung='" + lung + '\'' +
                ", kidney='" + kidney + '\'' +
                ", prostate='" + prostate + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
