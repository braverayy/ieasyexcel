package com.leslie.framework.ieasyexcel.rule;

/**
 * @author leslie
 * @date 2021/5/15
 */
public interface Rule<T> {

    Result execute(T bizData);
}
