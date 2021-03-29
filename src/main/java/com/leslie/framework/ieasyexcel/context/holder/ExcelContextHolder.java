package com.leslie.framework.ieasyexcel.context.holder;

import com.leslie.framework.ieasyexcel.apply.context.ApplyContext;
import com.leslie.framework.ieasyexcel.apply.context.ApplyContextHolder;
import com.leslie.framework.ieasyexcel.context.holder.strategy.ContextHolderStrategy;
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

    public static class READ {

        public static final String KEY_PREFIX = "READ.";

        public static void clearContext(String key) {
            ReadContextHolder.clearContext(KEY_PREFIX + key);
        }

        public static Optional<ReadContext> getContext(String key) {
            return ReadContextHolder.getContext(KEY_PREFIX + key);
        }

        public static void setContext(String key, ReadContext context) {
            ReadContextHolder.setContext(KEY_PREFIX + key, context);
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

        public static final String KEY_PREFIX = "APPLY.";

        public static void clearContext(String key) {
            ApplyContextHolder.clearContext(KEY_PREFIX + key);
        }

        public static Optional<ApplyContext> getContext(String key) {
            return ApplyContextHolder.getContext(KEY_PREFIX + key);
        }

        public static void setContext(String key, ApplyContext context) {
            ApplyContextHolder.setContext(KEY_PREFIX + key, context);
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
