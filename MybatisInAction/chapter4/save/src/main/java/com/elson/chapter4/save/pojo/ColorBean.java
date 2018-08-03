package com.elson.chapter4.save.pojo;

public class ColorBean {

    private Integer id;
    private Integer color;
    private String note;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
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
