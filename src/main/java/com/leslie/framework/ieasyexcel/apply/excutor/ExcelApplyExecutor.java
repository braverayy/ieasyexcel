package com.leslie.framework.ieasyexcel.apply.excutor;

import com.leslie.framework.ieasyexcel.apply.ExcelApplier;

/**
 * @author leslie
 * @date 2021/3/22
 */
public interface ExcelApplyExecutor<T> {

    void execute(String key, ExcelApplier<T> excelApplier);
}
