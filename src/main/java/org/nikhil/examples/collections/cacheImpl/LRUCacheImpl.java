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

    //Eviction policy: remove the eldest entry if the size exceeds MAX_ENTRIES
    // This method is called by put and putAll after inserting a new entry
    // and before returning from the method. By default, it returns false,
    // meaning that no entries are removed. We override it to implement LRU behavior.
    // LRU means that the least recently used entry will be removed when the cache exceeds its maximum size.
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

    // LinkedHashMap implements the Map interface, so we can use it as a cache.
    // Also it maintains the insertion order, which is useful for LRU cache implementation.
}
