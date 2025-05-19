package org.nikhil.examples.collections.cacheImpl;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<K, V> implements Cache<K, V> {

    private int size;
    private Map<K, V> cache;

    public LRUCache(int size) {
        this.size = size;
        this.cache = new LinkedHashMap<>(size);
    }

    @Override
    public boolean put(K key, V value) {

        this.cache.put(key, value);
        return true;
    }

    @Override
    public V get(K key) {
        return this.cache.get(key);
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size > 0;
    }

    @Override
    public void clear() {
        this.cache.clear();
    }

    @Override
    public String toString() {
        return this.cache.keySet().toString();
    }

    public static void main(String[] args) {
        Cache<Integer, String> lruCache = new LRUCache<>(3);
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
