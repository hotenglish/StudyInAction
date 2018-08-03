package com.elson.chapter4.association.pojo;

import java.util.List;

public class FemaleStudent extends Student {
    List<StudentHealthFemale> studentHealthFemaleList = null;

    @Override
    public String toString() {
        return super.toString() +"\n FemaleStudent{" +
                "studentHealthFemaleList=" + studentHealthFemaleList +
                '}';
    }
}
