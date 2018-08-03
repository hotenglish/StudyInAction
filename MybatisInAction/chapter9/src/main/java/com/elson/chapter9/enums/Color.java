package com.elson.chapter9.enums;

public enum Color {

    RED(1, "红"),
    YELLOW(2,"黄"),
    BLUE(3,"蓝");

    private int code;
    private String name;

    Color(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public static Color getEnumByCode(int code) {
        for (Color color : Color.values()) {
            if (color.code == code) {
                return color;
            }
        }
        return null;
    }

}
