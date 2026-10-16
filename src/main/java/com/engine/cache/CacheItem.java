package com.engine.cache;

public class CacheItem<V> {
    private final V value;
    private final long expiresAtMs;

    public CacheItem(V value, long ttlMs) {
        this.value = value;
        this.expiresAtMs = ttlMs > 0 ? System.currentTimeMillis() + ttlMs : -1;
    }

    public V getValue() {
        return value;
    }

    public boolean isExpired() {
        if (expiresAtMs == -1) return false;
        return System.currentTimeMillis() > expiresAtMs;
    }
}
