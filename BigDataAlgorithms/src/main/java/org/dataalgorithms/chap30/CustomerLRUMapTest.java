package org.dataalgorithms.chap30;

public class CustomerLRUMapTest {

    public static void main(String[] args) {
        CustomLRUMap<String, String> map = new CustomLRUMap<String, String>(3);
        map.put("k1", "v1");
        map.put("k2", "v2");
        map.put("k3", "v3");
        System.out.println("map=" + map);
        map.put("k4", "v4");
        String v = map.get("k2");
        System.out.println("v=" + v);
        System.out.println("map=" + map);
        map.put("k5", "v5");
        System.out.println("map=" + map);
        map.put("k6", "v6");
        System.out.println("map=" + map);
    }

}
