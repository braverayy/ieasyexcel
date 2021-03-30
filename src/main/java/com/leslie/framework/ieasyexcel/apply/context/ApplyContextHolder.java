package com.leslie.framework.ieasyexcel.apply.context;

import com.leslie.framework.ieasyexcel.context.holder.strategy.ContextHolderStrategy;
import com.leslie.framework.ieasyexcel.context.holder.strategy.LocalContextHolderStrategy;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Constructor;
import java.util.Optional;

/**
 * @author leslie
 * @date 2021/3/23
 */
public class ApplyContextHolder {

    private static ContextHolderStrategy<String, ApplyContext> strategy;

    public static final String KEY_PREFIX = "APPLY.";
    public static final String MODE_LOCAL = "APPLY_MODE_LOCAL";
    public static final String SYSTEM_PROPERTY = "ieasyexcel.apply.strategy";
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
                strategy = (ContextHolderStrategy<String, ApplyContext>) customStrategy.newInstance();
            } catch (Exception ex) {
                ReflectionUtils.handleReflectionException(ex);
            }
        }

        initializeCount++;
    }

    public static void clearContext(String key) {
        strategy.clearContext(KEY_PREFIX + key);
    }

    public static Optional<ApplyContext> getContext(String key) {
        return strategy.getContext(KEY_PREFIX + key);
    }

    public static void setContext(String key, ApplyContext context) {
        strategy.setContext(KEY_PREFIX + key, context);
    }

    public static void setStrategyName(String strategyName) {
        ApplyContextHolder.strategyName = strategyName;
        initialize();
    }

    public static ContextHolderStrategy<String, ApplyContext> getContextHolderStrategy() {
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
