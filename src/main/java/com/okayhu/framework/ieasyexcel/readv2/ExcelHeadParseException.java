package com.okayhu.framework.ieasyexcel.readv2;

/**
 * @author okayhu
 * @date 2021/12/7
 */
public class ExcelHeadParseException extends RuntimeException{

    public ExcelHeadParseException(String message) {
        super(message);
    }

    public ExcelHeadParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExcelHeadParseException(Throwable cause) {
        super(cause);
    }
}
