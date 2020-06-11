package org.dataalgorithms.chap30;

import org.apache.commons.collections4.map.LRUMap;

public class CustomLRUMap<K, V> extends LRUMap<K, V> {

    private K key = null;
    private V value = null;
    private LinkEntry<K, V> entry = null;

    public CustomLRUMap(final int size) {
        super(size);
    }

    protected boolean removeLRU(final LinkEntry<K, V> entry) {
        System.out.println("begin remove LRU entry ...");
        this.entry = entry;
        this.key = entry.getKey();
        this.value = entry.getValue();

        if (key instanceof String) {
            String keyAsString = (String) key;
            System.out.println("evicting key=" + keyAsString);
        }

        if (value instanceof MapDBEntry) {
            MapDBEntry mapDBEntry = (MapDBEntry) value;
            mapDBEntry.close();
        }
        return true;
    }

}

