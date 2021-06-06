package com.leslie.framework.ieasyexcel.support;

/**
 * @author leslie
 * @date 2021/6/6
 */
public class ExcelCommonException extends RuntimeException {

    public ExcelCommonException() {
    }

    public ExcelCommonException(String message) {
        super(message);
    }

    public ExcelCommonException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExcelCommonException(Throwable cause) {
        super(cause);
    }
}
