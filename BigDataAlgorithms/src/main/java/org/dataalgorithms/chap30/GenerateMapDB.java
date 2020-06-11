package org.dataalgorithms.chap30;

import org.mapdb.DB;
import org.mapdb.DBMaker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.concurrent.ConcurrentNavigableMap;

public class GenerateMapDB {

    public static void main(String args[]) throws Exception {
        String inputFileName = args[0];
        String mapdbName = args[1];
        create(inputFileName, mapdbName);
    }

    public static void create(String inputFileName, String madpdbName) throws Exception {
        DB db = DBMaker.newFileDB(new File(madpdbName)).closeOnJvmShutdown().make();
        ConcurrentNavigableMap<String, String> map = db.getTreeMap("collectionName");
        String line = null;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(inputFileName));
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                String[] tokens = line.split(";");
                if (tokens.length == 2) {
                    map.put(tokens[0], tokens[1]);
                } else {
                    System.out.println("error line=" + line);
                }
            }
        } finally {
            reader.close();
            db.commit();
            db.close();
        }
    }
}
