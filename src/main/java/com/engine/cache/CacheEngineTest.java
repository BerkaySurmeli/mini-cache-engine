package com.engine.cache;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CacheEngineTest {

    @Test
    void testBasicPutAndGet() {
        CacheEngine<String, String> cache = new CacheEngine<>(10);
        cache.put("foo", "bar", 0);
        assertEquals("bar", cache.get("foo"));
    }

    @Test
    void testTTLExpiration() throws InterruptedException {
        CacheEngine<String, String> cache = new CacheEngine<>(10);
        cache.put("temp", "val", 100); // 100ms TTL
        assertEquals("val", cache.get("temp"));
        
        Thread.sleep(150);
        assertNull(cache.get("temp"));
    }
}
