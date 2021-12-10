package com.okayhu.framework.ieasyexcel.readv2;

/**
 * @author okayhu
 * @date 2021/12/10
 */
public interface Reader {

    enum Type {
        ROW, PAGE
    }

    String getType();
}
