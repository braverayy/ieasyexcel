package com.leslie.framework.ieasyexcel.apply.excutor;


import com.leslie.framework.ieasyexcel.apply.ExcelApplier;
import com.leslie.framework.ieasyexcel.apply.ExcelApplyHandler;
import com.leslie.framework.ieasyexcel.apply.loader.ApplyContextLoader;
import com.leslie.framework.ieasyexcel.apply.loader.ApplyContextPageLoaderAdapter;
import com.leslie.framework.ieasyexcel.context.ApplyContext;
import com.leslie.framework.ieasyexcel.context.holder.ContextHolder;

import java.util.Objects;

/**
 * @author leslie
 * @date 2021/3/22
 */
public class ApplyExecutor {

    private final String key;

    private ExcelApplier excelApplier;

    private ExcelApplyHandler excelApplyHandler;

    private ApplyContextLoader contextLoader;

    private ContextHolder<String, ApplyContext> contextHolder;

    public ApplyExecutor(String key) {
        this.key = key;
    }

    /**
     * execute complete apply process
     */
    public void execute() {
        Objects.requireNonNull(excelApplier, "ExcelApplier must not be null!");
        Objects.requireNonNull(contextLoader, "ContextLoader must not be null!");
        Objects.requireNonNull(contextHolder, "ContextHolder must not be null!");

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
                    excelApplier.apply(data, context);
                }

            } while (context.getIndex() < context.getTotal() - 1);
        });
    }

    public void doExecute(Runnable runnable) {
        if (excelApplyHandler != null) {
            excelApplyHandler.beforeApply();

            runnable.run();

            excelApplyHandler.afterApply();
        }
    }

    public ApplyExecutor excelApplier(ExcelApplier excelApplier) {
        this.excelApplier = excelApplier;
        return this;
    }

    public ApplyExecutor excelApplyHandler(ExcelApplyHandler excelApplyHandler) {
        this.excelApplyHandler = excelApplyHandler;
        return this;
    }

    public ApplyExecutor contextLoader(ApplyContextLoader contextLoader) {
        this.contextLoader = contextLoader;
        return this;
    }

    public ApplyExecutor contextHolder(ContextHolder<String, ApplyContext> contextHolder) {
        this.contextHolder = contextHolder;
        return this;
    }
}
