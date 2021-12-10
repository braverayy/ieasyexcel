package com.okayhu.framework.ieasyexcel.readv2;

/**
 * @author okayhu
 * @date 2021/12/7
 */
@FunctionalInterface
public interface RowReader<T> extends Reader {

    void read(RowContext<T> rowContext);

    @Override
    default String getType() {
        return Type.ROW.name();
    }
}
