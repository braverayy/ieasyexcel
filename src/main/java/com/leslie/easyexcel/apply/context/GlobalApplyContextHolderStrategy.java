package com.leslie.easyexcel.apply.context;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 在一次入库过程中的所有实例共享相同的ApplyContext
 *
 * @author leslie
 * @date 2021/3/23
 */
public class GlobalApplyContextHolderStrategy implements ApplyContextHolderStrategy {

    private static final ConcurrentHashMap<String, ApplyContext> CONTEXT_HOLDER = new ConcurrentHashMap<>();

    @Override
    public Optional<ApplyContext> getContext(String key) {
        return Optional.ofNullable(CONTEXT_HOLDER.get(key));
    }

    @Override
    public void setContext(String key, ApplyContext context) {
        CONTEXT_HOLDER.put(key, context);
    }

    @Override
    public void clearContext(String key) {
        CONTEXT_HOLDER.remove(key);
    }
}
