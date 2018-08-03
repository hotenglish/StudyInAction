package com.elson.chapter9.pojo;

public class TFile {

    private Long id;
    private byte[] file;//用来保存BLOB字段

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }
}
