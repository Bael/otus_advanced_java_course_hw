package com.github.bael.cache;

import java.lang.ref.SoftReference;
import java.util.concurrent.ConcurrentHashMap;

public class Cache<T, ID> implements Cacheable<T, ID> {
    private final CacheLoadable<T, ID> cacheLoader;

    public Cache(CacheLoadable<T, ID> cacheLoader) {
        this.cacheLoader = cacheLoader;
        this.cacheInternal = new ConcurrentHashMap<>();
    }

    private final ConcurrentHashMap<ID, SoftReference<T>> cacheInternal;

    @Override
    public T get(ID id) {
        cacheInternal.computeIfAbsent(id, key -> new SoftReference<>(cacheLoader.load(id)));

        SoftReference<T> ref = cacheInternal.get(id);
        return ref.get();
    }

    @Override
    public void remove(ID id) {
        cacheInternal.remove(id);
    }

}
