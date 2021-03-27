package com.leslie.framework.ieasyexcel.apply.context;

import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Constructor;
import java.util.Optional;

/**
 * @author leslie
 * @date 2021/3/23
 */
public class ApplyContextHolder {

    public static final String MODE_GLOBAL = "APPLY_MODE_GLOBAL";
    public static final String SYSTEM_PROPERTY = "easyexcel.apply.strategy";
    private static String strategyName = System.getProperty(SYSTEM_PROPERTY);
    private static ApplyContextHolderStrategy strategy;
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
            strategy = new GlobalApplyContextHolderStrategy();
        } else {
            // Try to load a custom strategy
            try {
                Class<?> clazz = Class.forName(strategyName);
                Constructor<?> customStrategy = clazz.getConstructor();
                strategy = (ApplyContextHolderStrategy) customStrategy.newInstance();
            } catch (Exception ex) {
                ReflectionUtils.handleReflectionException(ex);
            }
        }

        initializeCount++;
    }

    public static void clearContext(String key) {
        strategy.clearContext(key);
    }

    public static Optional<ApplyContext> getContext(String key) {
        return strategy.getContext(key);
    }

    public static void setContext(String key, ApplyContext context) {
        strategy.setContext(key, context);
    }

    public static void setStrategyName(String strategyName) {
        ApplyContextHolder.strategyName = strategyName;
        initialize();
    }

    public static ApplyContextHolderStrategy getContextHolderStrategy() {
        return strategy;
    }

    public static int getInitializeCount() {
        return initializeCount;
    }

    @Override
    public String toString() {
        return "ApplyContextHolder[strategy='" + strategyName + "'; initializeCount="
                + initializeCount + "]";
    }
}
