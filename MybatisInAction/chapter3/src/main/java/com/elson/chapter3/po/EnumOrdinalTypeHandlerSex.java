package com.elson.chapter3.po;

public enum EnumOrdinalTypeHandlerSex {

    MALE(1, "男"), FEMALE(2, "女");

    private int id;
    private String name;

    private EnumOrdinalTypeHandlerSex(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static EnumOrdinalTypeHandlerSex getSex(int id) {
        if (id == 1) {
            return MALE;
        } else if (id == 2) {
            return FEMALE;
        }
        return null;
    }
}
