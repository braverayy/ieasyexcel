package com.okayhu.framework.ieasyexcel.core;

/**
 * Excel row data Validator
 *
 * @author okayhu
 * @date 2021/12/6
 */
@FunctionalInterface
public interface DataValidator<T> {

    /**
     * Based on Hibernate Validator, validate Excel row data
     *
     * @param rowData excel row data
     * @return validation result
     */
    ValidationResult validate(T rowData);
}
