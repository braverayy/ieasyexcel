package com.leslie.framework.ieasyexcel.context.holder.strategy;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.leslie.framework.ieasyexcel.context.Context;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.time.Duration;
import java.util.Optional;

/**
 * @author leslie
 * @date 2021/3/28
 */
public class LocalContextHolderStrategy<K, V extends Context> implements ContextHolderStrategy<K, V> {

    private static Cache<@NonNull Object, @NonNull Object> cache;

    @SuppressWarnings("unchecked")
    @Override
    public Optional<V> getContext(K key) {
        return Optional.ofNullable((V) cache.getIfPresent(key));
    }

    @Override
    public void setContext(K key, V context) {
        if (cache == null) {
            cache = Caffeine.newBuilder()
                    .expireAfterWrite(Duration.ofMinutes(5))
                    .build();
        }
        cache.put(key, context);
    }

    @Override
    public void clearContext(K key) {
        cache.invalidate(key);
    }
}
