package com.github.bael.cache;

/**
 * Интерфейс загрузки в кэш. (подсмотрено в гуава кэш)
 * @param <T>
 * @param <ID>
 */
@FunctionalInterface
public interface CacheLoadable<T, ID> {
    public T load(ID id);
}
