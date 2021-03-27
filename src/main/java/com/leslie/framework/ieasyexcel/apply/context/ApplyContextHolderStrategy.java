package com.leslie.framework.ieasyexcel.apply.context;

import java.util.Optional;

/**
 * @author leslie
 * @date 2021/3/22
 */
public interface ApplyContextHolderStrategy {

    Optional<ApplyContext> getContext(String key);

    void setContext(String key, ApplyContext context);

    void clearContext(String key);
}
