package com.leslie.framework.ieasyexcel.holder.strategy;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.time.Duration;
import java.util.Optional;

/**
 * @author leslie
 * @date 2021/3/28
 */
public class LocalContextHolderStrategy<K, V> implements ContextHolderStrategy<K, V> {

    private static Cache<Object, Object> cache;

    @SuppressWarnings("unchecked")
    @Override
    public Optional<V> getContext(K key) {
        return Optional.ofNullable((V) cache.getIfPresent(key));
    }

    @Override
    public void setContext(K key, V context) {
        if (cache == null) {
            cache = CacheBuilder.newBuilder()
                    .expireAfterAccess(Duration.ofMinutes(5))
                    .build();
        }
        cache.put(key, context);
    }

    @Override
    public void clearContext(K key) {
        cache.invalidate(key);
    }
}
