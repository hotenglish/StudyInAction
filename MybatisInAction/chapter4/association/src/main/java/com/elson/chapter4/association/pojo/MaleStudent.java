package com.elson.chapter4.association.pojo;

import java.util.List;

public class MaleStudent extends Student {
    private List<StudentHealthMale> studentHealthMaleList = null;

    @Override
    public String toString() {
        return super.toString() + "\n MaleStudent{" +
                "studentHealthMaleList=" + studentHealthMaleList +
                '}';
    }
}
