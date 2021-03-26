package com.leslie.easyexcel.apply;

import com.leslie.easyexcel.apply.context.ApplyContext;

/**
 * @author leslie
 * @date 2021/3/22
 */
public interface ExcelApplier<T> {

    void apply(T t, ApplyContext context);
}
