package com.elson.chapter4.save.dao;

import java.util.Map;

public interface ColorDao {

    Map<Object, Object> findColorByNote(String note);

}
