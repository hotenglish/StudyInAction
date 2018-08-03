package com.elson.chapter4.association.pojo;

public class StudentHealthFemale {
    private Integer id;
    private String student_id;
    private String check_date;
    private String heart;
    private String liver;
    private String spleen;
    private String lung;
    private String kidney;
    private String uterus;
    private String note;

    @Override
    public String toString() {
        return "StudentHealthFemale{" +
                "id=" + id +
                ", student_id='" + student_id + '\'' +
                ", check_date='" + check_date + '\'' +
                ", heart='" + heart + '\'' +
                ", liver='" + liver + '\'' +
                ", spleen='" + spleen + '\'' +
                ", lung='" + lung + '\'' +
                ", kidney='" + kidney + '\'' +
                ", uterus='" + uterus + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
