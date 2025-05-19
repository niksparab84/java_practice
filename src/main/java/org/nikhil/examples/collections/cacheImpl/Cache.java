package org.nikhil.examples.collections.cacheImpl;

import java.util.Optional;

public interface Cache<K, V> {
    boolean put(K key, V value);
    V get(K key);
    int size();
    boolean isEmpty();
    void clear();
}
