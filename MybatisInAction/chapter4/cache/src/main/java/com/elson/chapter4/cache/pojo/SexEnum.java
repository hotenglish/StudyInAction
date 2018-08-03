package com.elson.chapter4.cache.pojo;

import java.io.Serializable;

public enum SexEnum implements Serializable {

    SEX_MALE("男"),
    SEX_FEMALE("女"),
    SEX_UNKNOWN("未知");

    private final String text;

    SexEnum(final String text) {
        this.text = text;
    }

    public static String index(Integer index) {
        switch (index) {
            case 1:
                return SEX_MALE.toString();
            case 2:
                return SEX_FEMALE.toString();
            default:
                return SEX_UNKNOWN.toString();
        }
    }

    public static SexEnum entity(Integer index) {
        switch (index) {
            case 1:
                return SEX_MALE;
            case 2:
                return SEX_FEMALE;
            default:
                return SEX_UNKNOWN;
        }
    }

    @Override
    public String toString() {
        return text;
    }
}
