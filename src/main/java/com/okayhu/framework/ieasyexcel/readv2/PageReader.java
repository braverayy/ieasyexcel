package com.okayhu.framework.ieasyexcel.readv2;

import java.util.List;

/**
 * @author okayhu
 * @date 2021/12/7
 */
public interface PageReader<T> extends Reader {

    void pageRead(List<RowContext<T>> rowContexts);

    @Override
    default String getType() {
        return Type.PAGE.name();
    }
}
