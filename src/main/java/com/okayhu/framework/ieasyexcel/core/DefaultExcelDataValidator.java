package com.okayhu.framework.ieasyexcel.core;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author okayhu
 * @date 2021/12/6
 */
public class DefaultExcelDataValidator<T> extends AbstractExcelDataValidator<T> {

    public DefaultExcelDataValidator() {
    }

    public DefaultExcelDataValidator(Validator validator) {
        super(validator);
    }

    @Override
    public ValidationResult format(Set<ConstraintViolation<T>> violations) {
        if (violations == null || violations.isEmpty()) {
            return ValidationResult.success();
        }
        String errorMsg = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(";"));
        return ValidationResult.fail(errorMsg);
    }
}
