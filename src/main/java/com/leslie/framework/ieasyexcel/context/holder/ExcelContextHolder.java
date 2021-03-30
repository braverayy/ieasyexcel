package com.leslie.framework.ieasyexcel.context.holder;

import com.github.benmanes.caffeine.cache.Cache;
import com.leslie.framework.ieasyexcel.apply.context.ApplyContext;
import com.leslie.framework.ieasyexcel.apply.context.ApplyContextHolder;
import com.leslie.framework.ieasyexcel.context.Context;
import com.leslie.framework.ieasyexcel.context.holder.strategy.ContextHolderStrategy;
import com.leslie.framework.ieasyexcel.context.holder.strategy.LocalContextHolderStrategy;
import com.leslie.framework.ieasyexcel.read.context.ReadContext;
import com.leslie.framework.ieasyexcel.read.context.ReadContextHolder;

import java.util.Optional;

/**
 * Factory
 *
 * @author leslie
 * @date 2021/3/28
 */
public class ExcelContextHolder {

    /**
     * In most cases, you only need to call this method once in the system
     */
    public static void setLocalCache(Cache<String, Context> localCache) {
        LocalContextHolderStrategy.cache = localCache;
    }

    public static class READ {

        public static void clearContext(String key) {
            ReadContextHolder.clearContext(key);
        }

        public static Optional<ReadContext> getContext(String key) {
            return ReadContextHolder.getContext(key);
        }

        public static void setContext(String key, ReadContext context) {
            ReadContextHolder.setContext(key, context);
        }

        public static void setStrategyName(String strategyName) {
            ReadContextHolder.setStrategyName(strategyName);
        }

        public static ContextHolderStrategy<String, ReadContext> getContextHolderStrategy() {
            return ReadContextHolder.getContextHolderStrategy();
        }

        public static int getInitializeCount() {
            return ReadContextHolder.getInitializeCount();
        }
    }

    public static class APPLY {

        public static void clearContext(String key) {
            ApplyContextHolder.clearContext(key);
        }

        public static Optional<ApplyContext> getContext(String key) {
            return ApplyContextHolder.getContext(key);
        }

        public static void setContext(String key, ApplyContext context) {
            ApplyContextHolder.setContext(key, context);
        }

        public static void setStrategyName(String strategyName) {
            ApplyContextHolder.setStrategyName(strategyName);
        }

        public static ContextHolderStrategy<String, ApplyContext> getContextHolderStrategy() {
            return ApplyContextHolder.getContextHolderStrategy();
        }

        public static int getInitializeCount() {
            return ApplyContextHolder.getInitializeCount();
        }
    }
}
