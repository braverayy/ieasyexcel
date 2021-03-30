package com.leslie.framework.ieasyexcel.read.context;

import com.leslie.framework.ieasyexcel.context.holder.strategy.ContextHolderStrategy;
import com.leslie.framework.ieasyexcel.context.holder.strategy.LocalContextHolderStrategy;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Constructor;
import java.util.Optional;

/**
 * @author leslie
 * @date 2021/3/24
 */
public class ReadContextHolder {

    private static ContextHolderStrategy<String, ReadContext> strategy;

    public static final String KEY_PREFIX = "READ.";
    public static final String MODE_LOCAL = "READ_MODE_LOCAL";
    public static final String SYSTEM_PROPERTY = "ieasyexcel.read.strategy";
    private static String strategyName = System.getProperty(SYSTEM_PROPERTY);
    private static int initializeCount = 0;

    static {
        initialize();
    }

    @SuppressWarnings("unchecked")
    private static void initialize() {
        if (!StringUtils.hasText(strategyName)) {
            // Set default
            strategyName = MODE_LOCAL;
        }

        if (strategyName.equals(MODE_LOCAL)) {
            strategy = new LocalContextHolderStrategy<>();
        } else {
            // Try to load a custom strategy
            try {
                Class<?> clazz = Class.forName(strategyName);
                Constructor<?> customStrategy = clazz.getConstructor();
                strategy = (ContextHolderStrategy<String, ReadContext>) customStrategy.newInstance();
            } catch (Exception ex) {
                ReflectionUtils.handleReflectionException(ex);
            }
        }

        initializeCount++;
    }

    public static void clearContext(String key) {
        strategy.clearContext(KEY_PREFIX + key);
    }

    public static Optional<ReadContext> getContext(String key) {
        return strategy.getContext(KEY_PREFIX + key);
    }

    public static void setContext(String key, ReadContext context) {
        strategy.setContext(KEY_PREFIX + key, context);
    }

    public static void setStrategyName(String strategyName) {
        ReadContextHolder.strategyName = strategyName;
        initialize();
    }

    public static ContextHolderStrategy<String, ReadContext> getContextHolderStrategy() {
        return strategy;
    }

    public static int getInitializeCount() {
        return initializeCount;
    }

    @Override
    public String toString() {
        return "ReadContextHolder[strategy='" + strategyName + "'; initializeCount="
                + initializeCount + "]";
    }
}
