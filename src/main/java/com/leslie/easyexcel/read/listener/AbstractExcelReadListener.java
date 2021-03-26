package com.leslie.easyexcel.read.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.metadata.holder.ReadRowHolder;
import com.alibaba.excel.read.metadata.holder.ReadSheetHolder;
import com.leslie.easyexcel.read.context.ReadContext;
import com.leslie.easyexcel.read.context.ReadContextHolder;

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
        ReadRowHolder readRowHolder = context.readRowHolder();
        ReadSheetHolder readSheetHolder = context.readSheetHolder();

        ReadContext readContext = new ReadContext();
        readContext.setSheetNo(readSheetHolder.getSheetNo());
        readContext.setSheetName(readSheetHolder.getSheetName());
        readContext.setTotalRowNumber(readSheetHolder.getApproximateTotalRowNumber());
        readContext.setRowIndex(readRowHolder.getRowIndex());
        ReadContextHolder.setContext(key(), readContext);
    }
}
