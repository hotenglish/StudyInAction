/*
package org.dataalgorithms.chap30;

import java.util.Map;

public class CacheManager {

    private static final int DEFAULT_LRU_MAP_SIZE = 128;

    private static int theLRUMapSize = DEFAULT_LRU_MAP_SIZE;

    private static CustomLRUMap<String, MapDBEntry<String, String>> theCustomerLRUMap = null;

    private static BeginEndPosition beginend = null;

    private static String mapdbRootDirName = "/cache/mapdb/pf";

    private static String mapdbBeginEndDirName = "/cache/mapdb/pf/begin_end_position";

    private static boolean initialized = false;

    public static void setTheLRUMapSize(int size) {
        theLRUMapSize = size;
    }

    public static int getTheLRUMapSize() {
        return theLRUMapSize;
    }

    public static void init() throws Exception {
        if (initialized) {
            return;
        }

        theCustomerLRUMap = new CustomLRUMap<String, MapDBEntry<String, String>>(theLRUMapSize);
        beginend = new BeginEndPosition(mapdbBeginEndDirName);
        beginend.build(mapdbRootDirName);
        initialized = true;
    }

    public static void inti(int size) throws Exception {
        if (initialized) {
            return;
        }
        setTheLRUMapSize(size);
        init();
    }

    public static String get(int chrID, int position) throws Exception {
        return get(String.valueOf(chrID), String.valueOf(position));
    }

    public static String get(String chrID, String position) throws Exception {
        String dbName = getDBName(chrID, position);
        if (dbName == null) {
            return null;
        }
        MapDBEntry<String, String> entry = theCustomerLRUMap.get(dbName);
        if (entry == null) {
            entry = MapDBEntryFactory.create(dbName);
            theCustomerLRUMap.put(dbName, entry);
        }
        return entry.getValue(position);
    }

    private static String getDBName(String chrID, String position) {
        List<Internal> results = beginend.query(chrID, position);
        if ((results == null) || (results.isEmpty()) || (results.size() == 0)) {
            return null;
        } else {
            return results.get(0).db();
        }
    }

    public static void close() throws Exception {
        if (theCustomerLRUMap != null) {
            for (Map.Entry<String, MapDBEntry<String, String>> entry : theCustomerLRUMap.entrySet()) {
                entry.getValue().close();
            }
        }
    }
}
*/
