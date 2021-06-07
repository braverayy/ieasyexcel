package com.leslie.framework.ieasyexcel.apply;

import com.leslie.framework.ieasyexcel.context.ApplyContext;

/**
 * @author leslie
 * @date 2021/3/22
 */
public interface ExcelApplier {

    void apply(Object data, ApplyContext context);
}
