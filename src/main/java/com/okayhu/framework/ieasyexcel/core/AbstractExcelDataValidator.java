package com.okayhu.framework.ieasyexcel.core;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @author okayhu
 * @date 2021/12/6
 */
public abstract class AbstractExcelDataValidator<T> implements ExcelDataValidator<T> {

    protected final Validator validator;

    public AbstractExcelDataValidator() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    public AbstractExcelDataValidator(Validator validator) {
        this.validator = validator;
    }

    @Override
    public ValidationResult validate(T excelData) {
        Set<ConstraintViolation<T>> violations = validator.validate(excelData);
        return format(violations);
    }

    public abstract ValidationResult format(Set<ConstraintViolation<T>> violations);
}
