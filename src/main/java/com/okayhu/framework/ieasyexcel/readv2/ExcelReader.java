package com.okayhu.framework.ieasyexcel.readv2;

import com.alibaba.excel.context.AnalysisContext;
import com.okayhu.framework.ieasyexcel.core.ValidationResult;

/**
 * @author okayhu
 * @date 2021/12/7
 */
@FunctionalInterface
public interface ExcelReader<T> {

    void read(T rowData, ValidationResult validation, AnalysisContext analysisContext);
}
