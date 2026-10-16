package com.engine.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CacheEngine<K, V> {
    private final int capacity;
    private final Map<K, CacheItem<V>> store;

    public CacheEngine(int capacity) {
        this.capacity = capacity;
        this.store = new ConcurrentHashMap<>();
    }

    public void put(K key, V value, long ttlMs) {
        if (store.size() >= capacity && !store.containsKey(key)) {
            // Simple eviction trigger stub for benchmark extension
            K firstKey = store.keySet().iterator().next();
            store.remove(firstKey);
        }
        store.put(key, new CacheItem<>(value, ttlMs));
    }

    public V get(K key) {
        CacheItem<V> item = store.get(key);
        if (item == null) return null;
        if (item.isExpired()) {
            store.remove(key);
            return null;
        }
        return item.getValue();
    }

    public int size() {
        // Clean up expired items during size check
        store.entrySet().removeIf(entry -> entry.getValue().isExpired());
        return store.size();
    }
}
