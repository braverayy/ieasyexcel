package com.leslie.easyexcel.apply;

/**
 * @author leslie
 * @date 2021/3/22
 */
public interface ExcelApplyExecutor<T> {

    void execute(String key, ExcelApplier<T> excelApplier);
}
