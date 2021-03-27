package com.leslie.framework.ieasyexcel.support;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.function.Supplier;

/**
 * 入库结果构建器
 *
 * @author leslie
 * @date 2020/11/9
 */
@Getter
@Setter
@ToString
public class ExcelApplyResult {

    private boolean result;

    private String msg;

    public ExcelApplyResult() {
        this.result = true;
    }

    public ExcelApplyResult(boolean result, String msg) {
        this.result = result;
        this.msg = msg;
    }

    public ExcelApplyResult(ExcelApplyResultBuilder builder) {
        this.result = builder.result;
        this.msg = builder.msg;
    }

    public static ExcelApplyResultBuilder builder() {
        return new ExcelApplyResultBuilder();
    }

    public void ifTrue(Runnable run) {
        if (result) {
            run.run();
        }
    }

    public static class ExcelApplyResultBuilder {

        private boolean result;

        private String msg;

        ExcelApplyResultBuilder() {
            this.result = true;
        }

        public ExcelApplyResultBuilder(boolean result, String msg) {
            this.result = result;
            this.msg = msg;
        }

        public ExcelApplyResultBuilder andIfTrue(boolean filter, Supplier<ExcelApplyResult> supplier) {
            if (result && filter) {
                ExcelApplyResult excelApplyResult = supplier.get();
                this.result = excelApplyResult.isResult();
                this.msg = excelApplyResult.getMsg();
            }
            return this;
        }

        public ExcelApplyResultBuilder andIfTrue(Supplier<ExcelApplyResult> supplier) {
            if (result) {
                ExcelApplyResult excelApplyResult = supplier.get();
                this.result = excelApplyResult.isResult();
                this.msg = excelApplyResult.getMsg();
            }
            return this;
        }

        public ExcelApplyResult build() {
            return new ExcelApplyResult(this);
        }
    }
}


