package com.elson.hadoop.utils;

import java.io.File;

public class FileUtil {

    public static boolean deleteDir(String path) {
        File dir = new File(path);
        if (dir.exists()) {
            for (File f : dir.listFiles()) {
                if (f.isDirectory()) {
                    deleteDir(f.getName());
                } else {
                    f.delete();
                }
            }
            dir.delete();
            return true;
        } else {
            System.out.println("Folder does not exist!!!");
            return false;
        }
    }

}
