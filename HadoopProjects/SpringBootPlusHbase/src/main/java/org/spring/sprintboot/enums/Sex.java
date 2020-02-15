package com.elson.chapter9.enums;

public enum Sex {

    MALE(1, "男"),
    FEMALE(2, "女");

    private int code;
    private String name;

    Sex(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public static Sex getEnumByCode(int code) {
        for (Sex sex : Sex.values()) {
            if (sex.code == code) {
                return sex;
            }
        }
        return null;
    }
}
