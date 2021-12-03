package com.okayhu.framework.ieasyexcel.context.holder;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.okayhu.framework.ieasyexcel.context.Context;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.time.Duration;
import java.util.Optional;

/**
 * @author leslie
 * @date 2021/3/28
 */
public class LocalContextHolder<V extends Context> implements ContextHolder<String, V> {

    public Cache<@NonNull String, @NonNull V> cache;

    @Override
    public Optional<V> getContext(String key) {
        return cache == null ? Optional.empty() : Optional.ofNullable((V) cache.getIfPresent(key));
    }

    @Override
    public void setContext(String key, V context) {
        if (cache == null) {
            cache = Caffeine.newBuilder()
                    .expireAfterWrite(Duration.ofMinutes(5))
                    .build();
        }
        cache.put(key, context);
    }

    @Override
    public void clearContext(String key) {
        cache.invalidate(key);
    }
}
