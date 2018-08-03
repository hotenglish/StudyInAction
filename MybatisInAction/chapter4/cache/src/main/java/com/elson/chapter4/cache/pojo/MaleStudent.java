package com.elson.chapter4.cache.pojo;

import java.io.Serializable;
import java.util.List;

public class MaleStudent extends Student implements Serializable {
    private List<StudentHealthMale> studentHealthMaleList = null;

    @Override
    public String toString() {
        return super.toString() + "\n MaleStudent{" +
                "studentHealthMaleList=" + studentHealthMaleList +
                '}';
    }
}
