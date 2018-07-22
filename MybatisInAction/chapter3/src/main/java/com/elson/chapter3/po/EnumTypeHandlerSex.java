package com.elson.chapter3.po;

public enum EnumTypeHandlerSex {

    MALE("男"), FEMALE("女");

    private String sex;

    private EnumTypeHandlerSex(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

}
