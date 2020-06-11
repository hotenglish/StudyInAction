package org.dataalgorithms.chap30;

import org.mapdb.DB;
import org.mapdb.DBMaker;

import java.io.File;
import java.util.Map;

public class MapDBEntry<K, V> {

    private DB db = null;
    private Map<String, String> map = null;

    public MapDBEntry(DB db, Map<String, String> map) {
        this.db = db;
        this.map = map;
    }

    public String getValue(String key) {
        if (map == null) {
            return null;
        }
        return map.get(key);
    }

    public void close() {
        closeDB();
        closeMap();
    }

    public void closeDB() {
        if (db != null) {
            db.close();
        }
    }

    private void closeMap() {
        if (map != null) {
            map = null;
        }
    }

    public static MapDBEntry create(String dbName) {
        DB db = DBMaker.newFileDB(new File(dbName)).closeOnJvmShutdown().readOnly().make();
        Map<String, String> map = db.getTreeMap("collectionName");
        MapDBEntry entry = new MapDBEntry(db, map);
        return entry;
    }

}
