package com.leslie.framework.ieasyexcel.read.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.metadata.holder.ReadRowHolder;
import com.alibaba.excel.read.metadata.holder.ReadSheetHolder;
import com.leslie.framework.ieasyexcel.context.ReadContext;
import com.leslie.framework.ieasyexcel.context.holder.ContextHolder;
import com.leslie.framework.ieasyexcel.context.holder.ContextHolderBuilder;

/**
 * @author leslie
 * @date 2021/3/24
 */
public abstract class AbstractExcelReadListener<T> extends AnalysisEventListener<T> {

    @Override
    public void invoke(T data, AnalysisContext context) {
        setReadContext(context);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        setReadContext(context);
    }

    protected abstract String key();

    protected void setReadContext(AnalysisContext context) {
        ContextHolder<String, ReadContext> readContextHolder = ContextHolderBuilder.<ReadContext>create().build();

        ReadRowHolder readRowHolder = context.readRowHolder();
        ReadSheetHolder readSheetHolder = context.readSheetHolder();

        ReadContext readContext = ReadContext.builder()
                .sheetName(readSheetHolder.getSheetName())
                .sheetNo(readSheetHolder.getSheetNo())
                .rowTotal(readSheetHolder.getApproximateTotalRowNumber())
                .rowIndex(readRowHolder.getRowIndex())
                .build();

        readContextHolder.setContext(key(), readContext);
    }
}
