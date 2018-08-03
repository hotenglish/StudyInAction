package com.elson.chapter4.cache.pojo;

import java.io.Serializable;
import java.util.List;

public class FemaleStudent extends Student implements Serializable {
    List<StudentHealthFemale> studentHealthFemaleList = null;

    @Override
    public String toString() {
        return super.toString() + "\n FemaleStudent{" +
                "studentHealthFemaleList=" + studentHealthFemaleList +
                '}';
    }
}
