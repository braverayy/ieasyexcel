package com.okayhu.framework.ieasyexcel.core;

/**
 * @author okayhu
 * @date 2021/12/6
 */
public interface ExcelDataValidator<T> {

    ValidationResult validate(T excelData);
}
