package com.leslie.framework.ieasyexcel.context.holder.strategy;

import com.leslie.framework.ieasyexcel.context.Context;

import java.util.Optional;

/**
 * @author leslie
 * @date 2021/3/28
 */
public interface ContextHolderStrategy<String, V extends Context> {

    Optional<V> getContext(String key);

    void setContext(String key, V context);

    void clearContext(String key);
}
