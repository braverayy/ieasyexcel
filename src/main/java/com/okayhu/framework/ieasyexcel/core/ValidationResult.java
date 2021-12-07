package com.okayhu.framework.ieasyexcel.core;

import lombok.Data;

import java.io.Serializable;

/**
 * @author okayhu
 * @date 2021/12/6
 */
@Data
public class ValidationResult implements Serializable {

    private static final long serialVersionUID = 1008170410966980978L;

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
