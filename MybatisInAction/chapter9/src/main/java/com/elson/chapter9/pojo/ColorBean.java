package com.elson.chapter9.pojo;

import com.elson.chapter9.enums.Color;

public class ColorBean {

    private Integer id;
    private Color color;
    private String note;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "[id:" + id + " color:" + color + " note:" + note + "]";
    }
}
