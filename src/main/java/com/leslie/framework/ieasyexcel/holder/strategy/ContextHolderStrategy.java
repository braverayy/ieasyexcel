package com.leslie.framework.ieasyexcel.holder.strategy;

import java.util.Optional;

/**
 * @author leslie
 * @date 2021/3/28
 */
public interface ContextHolderStrategy<K, V> {

    Optional<V> getContext(K key);

    void setContext(K key, V context);

    void clearContext(K key);
}
