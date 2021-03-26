package com.leslie.easyexcel.apply.excutor;


import com.leslie.easyexcel.apply.ExcelApplier;
import com.leslie.easyexcel.apply.context.ApplyContext;
import com.leslie.easyexcel.apply.handler.ExcelApplyHandler;
import com.leslie.easyexcel.apply.handler.ExcelApplyHandlerImpl;
import com.leslie.easyexcel.apply.loader.ApplyContextLoader;

/**
 * @author leslie
 * @date 2021/3/22
 */
public class DefaultExcelApplyExcutor<T> implements ExcelApplyExecutor<T> {

    protected final ApplyContextLoader contextLoader;

    protected final ExcelApplyHandler handler;

    public DefaultExcelApplyExcutor(ApplyContextLoader contextLoader) {
        this.contextLoader = contextLoader;
        this.handler = new ExcelApplyHandlerImpl();
    }

    public DefaultExcelApplyExcutor(ExcelApplyHandler handler, ApplyContextLoader contextLoader) {
        this.handler = handler;
        this.contextLoader = contextLoader;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void execute(String key, ExcelApplier<T> excelApplier) {
        handler.beforeApply();

        for (ApplyContext context = this.contextLoader.next(key);
             !context.getIndex().equals(context.getTotal());
             context = this.contextLoader.next(key)) {

            T currentData = (T) context.getCurrentData();

            if (currentData != null) {
                excelApplier.apply(currentData, context);
            }
        }

        handler.afterApply();
    }
}
