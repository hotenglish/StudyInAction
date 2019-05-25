package com.learn.chapter8.service.impl;

import com.learn.chapter8.dao.FileDao;
import com.learn.chapter8.pojo.FileBean;
import com.learn.chapter8.service.FileService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Date;

@Service
public class FileServiceImpl implements FileService {

    private static Logger logger = Logger.getLogger(FileService.class.getName());

    @Autowired
    private FileDao fileDao = null;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public boolean insertFile(MultipartFile imgFile, FileBean file) {
        String filePath = "/home/oracle/learn/Servers" + new Date().getTime() + imgFile.getOriginalFilename();
        file.setFilePath(filePath);
        fileDao.insertFile(file);
        uploadFile(imgFile, filePath);
        return true;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public boolean insertFileVersion2(FileBean file) {
        fileDao.insertFile(file);
        return true;
    }

    @Override
    public void uploadFile(MultipartFile imgFile, String filePath) {
        FileOutputStream os = null;
        InputStream in = null;
        try {
            os = new FileOutputStream(filePath);
            in = imgFile.getInputStream();
            byte[] b = new byte[1024];
            while (in.read(b) != -1) {
                os.write(b);
            }
            os.flush();
        } catch (Exception ex) {
            logger.error(ex);
            throw new RuntimeException("文件上传失败");
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException ex) {
                logger.error(ex);
            }
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                logger.error(ex);
            }
        }
    }

}
