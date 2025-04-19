package com.github.bael.cache;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CacheTest {

    private final HashMap<String, Integer> cacheHitCount = new HashMap<>();

    private final CacheLoadable<String, String> cacheCounterLoader = s -> {
        cacheHitCount.put(s, 1 + cacheHitCount.getOrDefault(s, 0));
        return s;
    };

    @BeforeEach
    void setUp() {
        cacheHitCount.clear();
    }
    @Test
    void testLoadExecutedOnFirstLoadOnly() {
        Cache<String, String> cache = new Cache<>(cacheCounterLoader);
        String first = cache.get("first");
        assertEquals(1, cacheHitCount.getOrDefault(first, 0));
        assertEquals("first", first);

        String firstOneMoreTime = cache.get("first");
        assertEquals(1, cacheHitCount.getOrDefault(first, 0));
        assertEquals("first", firstOneMoreTime);
    }


    @Test
    void testCacheWithBrokenLoaderThrowsException() {
        Cache<String, String> cache = new Cache<>(s -> {
            throw new CacheLoadingException("Broken loader");
        });
        assertThrows(CacheLoadingException.class, () -> cache.get("first"));
    }

}
