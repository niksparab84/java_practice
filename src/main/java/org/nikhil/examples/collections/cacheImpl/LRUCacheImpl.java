package org.nikhil.examples.collections.cacheImpl;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCacheImpl<K, V> extends LinkedHashMap<K, V> {
    private int size;

    private static final int MAX_ENTRIES = 3;

    public LRUCacheImpl(int size) {
        super(size, 0.75F, true);
        this.size = size;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > MAX_ENTRIES;
    }

    public static void main(String[] args) {
        LRUCacheImpl<Integer, String> lruCache = new LRUCacheImpl<>(3);
        System.out.println(lruCache);

        lruCache.put(1, "Val1");
        lruCache.put(2, "Val2");
        lruCache.put(3, "Val3");
        System.out.println(lruCache);

        lruCache.put(4, "Val4");
        lruCache.put(5, "Val5");
        System.out.println(lruCache);

        System.out.println("get(3) => " + lruCache.get(3));
        System.out.println(lruCache);
    }
}
