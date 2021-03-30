package com.leslie.framework.ieasyexcel.apply.excutor;


import com.leslie.framework.ieasyexcel.apply.ExcelApplier;
import com.leslie.framework.ieasyexcel.apply.context.ApplyContext;
import com.leslie.framework.ieasyexcel.apply.handler.ExcelApplyHandler;
import com.leslie.framework.ieasyexcel.apply.handler.ExcelApplyHandlerImpl;
import com.leslie.framework.ieasyexcel.apply.loader.ApplyContextLoader;

/**
 * @author leslie
 * @date 2021/3/22
 */
public class DefaultExcelApplyExecutor<T> implements ExcelApplyExecutor<T> {

    protected final ApplyContextLoader contextLoader;

    protected final ExcelApplyHandler handler;

    public DefaultExcelApplyExecutor(ApplyContextLoader contextLoader) {
        this.contextLoader = contextLoader;
        this.handler = new ExcelApplyHandlerImpl();
    }

    public DefaultExcelApplyExecutor(ExcelApplyHandler handler, ApplyContextLoader contextLoader) {
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
