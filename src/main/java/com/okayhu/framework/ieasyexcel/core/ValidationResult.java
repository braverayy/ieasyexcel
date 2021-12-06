package com.okayhu.framework.ieasyexcel.core;

import lombok.Data;

/**
 * @author okayhu
 * @date 2021/12/6
 */
@Data
public class ValidationResult {

    private Boolean result;
    private String message;

    public ValidationResult(Boolean result, String message) {
        this.result = result;
        this.message = message;
    }

    public static ValidationResult success() {
        return new ValidationResult(true, "");
    }

    public static ValidationResult fail(String message) {
        return new ValidationResult(false, message);
    }
}
