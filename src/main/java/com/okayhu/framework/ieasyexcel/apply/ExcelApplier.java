package com.okayhu.framework.ieasyexcel.apply;

import com.okayhu.framework.ieasyexcel.context.ApplyContext;

/**
 * @author leslie
 * @date 2021/3/22
 */
public interface ExcelApplier {

    void apply(Object data, ApplyContext context);
}
