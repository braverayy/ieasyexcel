package com.leslie.easyexcel.apply;


import com.leslie.easyexcel.apply.context.ApplyContext;
import com.leslie.easyexcel.apply.loader.ApplyContextLoader;

/**
 * @author leslie
 * @date 2021/3/22
 */
public class DefaultExcelApplyExcutor<T> implements ExcelApplyExecutor<T> {

    private final ApplyContextLoader contextLoader;

    private ExcelApplyHandler handler = new ExcelApplyHandlerImpl();

    public DefaultExcelApplyExcutor(ApplyContextLoader contextLoader) {
        this.contextLoader = contextLoader;
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
