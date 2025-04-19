package com.github.bael.cache;

/**
 * интерфейс кэша, кэш отвечает лишь за отдачу элемента и его удаление, загрузка делается отдельным интерфейсом
 * {@link com.github.bael.cache.CacheLoadable}
 * @param <T>
 * @param <ID>
 */
public interface Cacheable<T, ID> {

    T get(ID id);
    void remove(ID id);

}
