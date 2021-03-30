package com.leslie.framework.ieasyexcel.read;

import com.alibaba.excel.EasyExcel;
import com.leslie.framework.ieasyexcel.context.holder.ExcelContextHolder;
import com.leslie.framework.ieasyexcel.read.listener.ExcelReadListener;
import com.leslie.framework.ieasyexcel.read.metadata.ExcelReadFlowControl;
import com.leslie.framework.ieasyexcel.read.metadata.ExcelReadValidation;
import com.leslie.framework.ieasyexcel.read.metadata.UserExcel;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

/**
 * @author leslie
 * @date 2021/3/30
 */
class ExcelReaderTest {

    InputStream inputStream = getClass().getClassLoader().getResourceAsStream("用户测试导入.xlsx");

    @Test
    void read() {
        long excelReadRecord = 1L;
        String key = String.valueOf(excelReadRecord);

        new Thread(() -> {
            while (true) {
                ExcelContextHolder.READ.getContext(key).ifPresent(System.out::println);
            }
        }).start();

        ExcelReadListener<? extends ExcelReadValidation> excelReadListener = new ExcelReadListener<>(key, (excelDataList, context) -> {
            excelDataList.forEach(System.out::println);
        }, ExcelReadFlowControl.builder().batchCount(2).build());

        read(excelReadListener, UserExcel.class, inputStream);
    }

    public <T extends ExcelReadValidation> void read(ExcelReadListener<? extends ExcelReadValidation> excelReadListener, Class<T> excelClass, InputStream inputStream) {
        EasyExcel.read(inputStream, excelClass, excelReadListener).sheet().doRead();
    }
}