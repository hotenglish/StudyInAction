package com.elson.chapter9.main;

import com.elson.chapter9.mapper.FileMapper;
import com.elson.chapter9.pojo.TFile;
import com.elson.chapter9.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;

import java.io.File;
import java.io.FileInputStream;

public class C9BlobMain {

    public static void main(String args[]) throws Exception {
        //insert();
        getFile();
    }

    private static void insert() throws Exception {
        File f = new File("C:\\Users\\B-36244\\Desktop\\messaging-ft-test.txt");
        FileInputStream in = new FileInputStream(f);
        byte[] bytes = new byte[(int) f.length()];
        try {
            in.read(bytes);
        } finally {
            in.close();
        }
        TFile tfile = new TFile();
        tfile.setFile(bytes);
        SqlSession session = SqlSessionFactoryUtil.openSqlSession();
        try {
            FileMapper fileMapper = session.getMapper(FileMapper.class);
            fileMapper.insertFile(tfile);
            System.err.println(tfile.getId());
            session.commit();
        } catch (Exception ex) {
            session.rollback();
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    private static void getFile() {
        SqlSession session = SqlSessionFactoryUtil.openSqlSession();
        try {
            FileMapper fileMapper = session.getMapper(FileMapper.class);
            TFile file = fileMapper.getFile(1);
            System.err.println(file.getFile().length);
        } catch (Exception ex) {
            session.rollback();
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
