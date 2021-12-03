package com.okayhu.framework.ieasyexcel.read.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.metadata.holder.ReadRowHolder;
import com.alibaba.excel.read.metadata.holder.ReadSheetHolder;
import com.okayhu.framework.ieasyexcel.context.ReadContext;
import com.okayhu.framework.ieasyexcel.context.holder.ContextHolder;

/**
 * @author leslie
 * @date 2021/3/24
 */
public abstract class AbstractExcelReadListener<T> extends AnalysisEventListener<T> {

    protected abstract ContextHolder<String, ReadContext> contextHolder();

    protected abstract String key();

    protected void setReadContext(AnalysisContext context) {
        ReadRowHolder readRowHolder = context.readRowHolder();
        ReadSheetHolder readSheetHolder = context.readSheetHolder();

        ReadContext readContext = ReadContext.builder()
                .sheetName(readSheetHolder.getSheetName())
                .sheetNo(readSheetHolder.getSheetNo())
                .rowTotal(readSheetHolder.getApproximateTotalRowNumber())
                .rowIndex(readRowHolder.getRowIndex())
                .build();
        contextHolder().setContext(key(), readContext);
    }
}
