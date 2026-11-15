package org.nikhil.examples.collections.cacheImpl;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCacheImpl<K, V> extends LinkedHashMap<K, V> {
    private final int size;

    private final int MAX_ENTRIES; // = 3;

    public LRUCacheImpl(int size, int maxEntries) {
        super(size, 0.75F, true); // Constructs an empty LinkedHashMap instance with the specified initial capacity, load factor and ordering mode.
        this.size = size;
        this.MAX_ENTRIES = maxEntries;
    }

    // Here we are using the constructor with only size parameter, so we will set MAX_ENTRIES to size.
    // We can keep MAX_ENTRIES different from size, that way we can have a cache with a maximum size of MAX_ENTRIES,
    // but we can have a smaller size for the initial capacity of the cache.
    // This is useful when we want to have a cache with a maximum size of MAX_ENTRIES, but we want to have a smaller
    // size for the initial capacity of the cache, so that we can have a cache with a maximum size of MAX_ENTRIES,
    // but we can have a smaller size for the initial capacity of the cache, so that we can have a cache with a maximum
    // size of MAX_ENTRIES, but we can have a smaller size for the initial capacity of the cache,
    public LRUCacheImpl(int size) {
        super(size, 0.75F, true);
        this.size = size;
        this.MAX_ENTRIES = size;
    }

    //Eviction policy: remove the eldest entry if the size exceeds MAX_ENTRIES
    // This method is called by put and putAll after inserting a new entry
    // and before returning from the method. By default, it returns false,
    // meaning that no entries are removed. We override it to implement LRU behavior.
    // LRU means that the least recently used entry will be removed when the cache exceeds its maximum size.
    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        System.out.println("removeEldestEntry is called, eldest => " + eldest);
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
    // What is the other way to implement LRU cache?
    // We can use a HashMap and a Doubly Linked List. The HashMap will store the key-value pairs, and the Doubly
    // Linked List will store the order of the keys. When we access a key, we will move it to the front of the list.
    // When we need to evict a key, we will remove the key from the end of the list. This way, we can achieve O(1)
    // time complexity for get and put operations. However, this implementation is more complex than using LinkedHashMap.

}
