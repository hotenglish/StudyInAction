package org.dataalgorithms.chap30;

import org.apache.log4j.Logger;
import org.mapdb.DB;
import org.mapdb.DBMaker;

import java.io.File;
import java.util.Map;

public class QueryMapDB {

    private static Logger THE_LOGGER = Logger.getLogger(QueryMapDB.class);

    public static void main(String args[]) throws Exception {
        long beginTime = System.currentTimeMillis();
        String mapdbName = args[0];
        String position = args[1];
        THE_LOGGER.info("mapdbName=" + mapdbName);
        THE_LOGGER.info("position=" + position);
        String value = query(mapdbName, position);
        THE_LOGGER.info("value=" + value);
        long elapsedTime = System.currentTimeMillis() - beginTime;
        THE_LOGGER.info("elapsedTime (in mills) =" + elapsedTime);
        System.exit(0);
    }

    public static String query(String mapdbName, String key) throws Exception {
        String value = null;
        DB db = null;
        try {
            db = DBMaker.newFileDB(new File(mapdbName)).closeOnJvmShutdown().readOnly().make();
            Map<String, String> map = db.getTreeMap("collectionName");
            value = map.get(key);
        } finally {
            if (db != null) {
                db.close();
            }
        }
        return value;
    }

}
