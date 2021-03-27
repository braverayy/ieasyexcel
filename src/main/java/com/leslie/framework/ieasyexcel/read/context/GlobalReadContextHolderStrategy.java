package com.leslie.framework.ieasyexcel.read.context;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author leslie
 * @date 2021/3/23
 */
public class GlobalReadContextHolderStrategy implements ReadContextHolderStrategy {

    private static final ConcurrentHashMap<String, ReadContext> CONTEXT_HOLDER = new ConcurrentHashMap<>();

    @Override
    public Optional<ReadContext> getContext(String key) {
        return Optional.ofNullable(CONTEXT_HOLDER.get(key));
    }

    @Override
    public void setContext(String key, ReadContext context) {
        CONTEXT_HOLDER.put(key, context);
    }

    @Override
    public void clearContext(String key) {
        CONTEXT_HOLDER.remove(key);
    }
}
