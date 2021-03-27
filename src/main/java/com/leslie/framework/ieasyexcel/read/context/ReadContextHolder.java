package com.leslie.framework.ieasyexcel.read.context;

import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Constructor;
import java.util.Optional;

/**
 * @author leslie
 * @date 2021/3/24
 */
public class ReadContextHolder {

    public static final String MODE_GLOBAL = "READ_MODE_GLOBAL";
    public static final String SYSTEM_PROPERTY = "easyexcel.read.strategy";
    private static String strategyName = System.getProperty(SYSTEM_PROPERTY);
    private static ReadContextHolderStrategy strategy;
    private static int initializeCount = 0;

    static {
        initialize();
    }

    private static void initialize() {
        if (!StringUtils.hasText(strategyName)) {
            // Set default
            strategyName = MODE_GLOBAL;
        }

        if (strategyName.equals(MODE_GLOBAL)) {
            strategy = new GlobalReadContextHolderStrategy();
        } else {
            // Try to load a custom strategy
            try {
                Class<?> clazz = Class.forName(strategyName);
                Constructor<?> customStrategy = clazz.getConstructor();
                strategy = (ReadContextHolderStrategy) customStrategy.newInstance();
            } catch (Exception ex) {
                ReflectionUtils.handleReflectionException(ex);
            }
        }

        initializeCount++;
    }

    public static void clearContext(String key) {
        strategy.clearContext(key);
    }

    public static Optional<ReadContext> getContext(String key) {
        return strategy.getContext(key);
    }

    public static void setContext(String key, ReadContext context) {
        strategy.setContext(key, context);
    }

    public static void setStrategyName(String strategyName) {
        ReadContextHolder.strategyName = strategyName;
        initialize();
    }

    public static ReadContextHolderStrategy getContextHolderStrategy() {
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
