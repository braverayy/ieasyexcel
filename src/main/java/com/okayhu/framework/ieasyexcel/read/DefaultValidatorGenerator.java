package com.okayhu.framework.ieasyexcel.read;

import javax.validation.Validation;
import javax.validation.Validator;

/**
 * @author hdy
 * @date 2021/12/3
 */
public class DefaultValidatorGenerator implements ValidatorGenerator {

    @Override
    public Validator generate() {
        return Validation.buildDefaultValidatorFactory().getValidator();
    }
}
