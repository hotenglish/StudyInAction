package com.elson.chapter9.pojo;

public class Bill {

    private Integer id;
    private String billName;
    private String note;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBillName() {
        return billName;
    }

    public void setBillName(String billName) {
        this.billName = billName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "[id:" + id + " billName:" + billName + " note:" + note + "]";
    }
}
