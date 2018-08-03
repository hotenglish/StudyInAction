package com.elson.chapter9.mapper;

import com.elson.chapter9.pojo.TFile;

public interface FileMapper {

    void insertFile(TFile file);

    TFile getFile(int id);

}
