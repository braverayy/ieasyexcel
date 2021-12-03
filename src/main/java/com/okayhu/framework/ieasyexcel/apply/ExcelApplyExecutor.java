package com.okayhu.framework.ieasyexcel.apply;


import com.okayhu.framework.ieasyexcel.apply.loader.ApplyContextLoader;
import com.okayhu.framework.ieasyexcel.apply.loader.ApplyContextPageLoaderAdapter;
import com.okayhu.framework.ieasyexcel.context.ApplyContext;
import com.okayhu.framework.ieasyexcel.context.holder.ContextHolder;
import lombok.Getter;

/**
 * @author leslie
 * @date 2021/3/22
 */
@Getter
public class ExcelApplyExecutor<T> {

    private final ExcelApplyParam applyParam;

    public ExcelApplyExecutor(ExcelApplyParam applyParam) {
        this.applyParam = applyParam;
    }

    /**
     * execute complete apply process
     */
    public void execute() {
        String key = applyParam.getKey();
        ApplyContextLoader contextLoader = applyParam.getContextLoader();
        ContextHolder<String, ApplyContext> contextHolder = applyParam.getContextHolder();

        doExecute(() -> {
            if (contextLoader instanceof ApplyContextPageLoaderAdapter) {
                ((ApplyContextPageLoaderAdapter<?>) contextLoader).setContextHolder(contextHolder);
            }

            ApplyContext context;
            do {

                context = contextLoader.next(key);
                contextHolder.setContext(key, context);

                Object data = context.getData();
                if (data != null) {
                    applyParam.getExcelApplier().apply(data, context);
                }

            } while (context.getIndex() < context.getTotal() - 1);
        });
    }

    private void doExecute(Runnable runnable) {
        ExcelApplyHandler applyHandler = applyParam.getExcelApplyHandler();
        if (applyHandler != null) {
            applyHandler.beforeApply();

            runnable.run();

            applyHandler.afterApply();
        } else {
            runnable.run();
        }
    }
}
