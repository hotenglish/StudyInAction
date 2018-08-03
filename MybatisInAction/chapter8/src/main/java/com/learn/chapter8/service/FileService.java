package com.learn.chapter8.service;

import com.learn.chapter8.pojo.FileBean;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    boolean insertFile(MultipartFile imgFile, FileBean file);

    boolean insertFileVersion2(FileBean file);

    void uploanFile(MultipartFile imgFile, String filePath);

}
