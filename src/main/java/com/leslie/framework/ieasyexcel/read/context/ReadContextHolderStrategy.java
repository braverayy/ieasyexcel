package com.leslie.framework.ieasyexcel.read.context;

import java.util.Optional;

/**
 * @author leslie
 * @date 2021/3/22
 */
public interface ReadContextHolderStrategy {

    Optional<ReadContext> getContext(String key);

    void setContext(String key, ReadContext context);

    void clearContext(String key);
}
