package com.okayhu.framework.ieasyexcel.context.holder;

import com.okayhu.framework.ieasyexcel.support.ExcelCommonException;
import com.okayhu.framework.ieasyexcel.context.Context;

/**
 * @author leslie
 * @date 2021/6/6
 */
public class ContextHolderBuilder<T extends Context> {

    private Class<? extends ContextHolder<String, T>> strategy;

    public static <T extends Context> ContextHolderBuilder<T> create() {
        return new ContextHolderBuilder<>();
    }

    public ContextHolderBuilder<T> strategy(Class<? extends ContextHolder<String, T>> strategy) {
        this.strategy = strategy;
        return this;
    }

    public ContextHolder<String, T> build() {
        if (strategy == null) {
            return new LocalContextHolder<>();
        }
        try {
            return strategy.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new ExcelCommonException(e);
        }
    }
}
