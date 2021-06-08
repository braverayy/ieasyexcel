package com.leslie.framework.ieasyexcel.read;

import com.leslie.framework.ieasyexcel.context.ReadContext;
import com.leslie.framework.ieasyexcel.context.holder.ContextHolder;
import com.leslie.framework.ieasyexcel.context.holder.ContextHolderBuilder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

/**
 * @author leslie
 * @date 2021/6/7
 */
@Getter
@Setter
public class ExcelReadParam {

    private String key;

    private ExcelReader<?> excelReader;

    private ContextHolder<String, ReadContext> contextHolder;

    private int batchCount;

    private boolean preCheck;

    private boolean checkHead;

    private boolean checkCacheRepeat;

    ExcelReadParam(String key,
                   ExcelReader<?> excelReader,
                   ContextHolder<String, ReadContext> contextHolder,
                   int batchCount,
                   boolean preCheck,
                   boolean checkHead,
                   boolean checkCacheRepeat) {
        Assert.notNull(key, "Key must not be null!");
        Assert.notNull(excelReader, "ExcelReader must not be null!");

        this.key = key;
        this.excelReader = excelReader;
        this.contextHolder = contextHolder;
        this.batchCount = batchCount;
        this.preCheck = preCheck;
        this.checkHead = checkHead;
        this.checkCacheRepeat = checkCacheRepeat;
    }

    public static ExcelReadParamBuilder builder() {
        return new ExcelReadParamBuilder();
    }

    public static class ExcelReadParamBuilder {
        private String key;
        private ExcelReader<?> excelReader;
        private ContextHolder<String, ReadContext> contextHolder = ContextHolderBuilder.<ReadContext>create().build();
        private int batchCount = 100;
        private boolean preCheck = true;
        private boolean checkHead = true;
        /**
         * Equals And HashCode method must be rewritten
         */
        private boolean checkCacheRepeat = true;

        ExcelReadParamBuilder() {
        }

        public ExcelReadParamBuilder key(String key) {
            this.key = key;
            return this;
        }

        public ExcelReadParamBuilder excelReader(ExcelReader<?> excelReader) {
            this.excelReader = excelReader;
            return this;
        }

        public ExcelReadParamBuilder contextHolder(ContextHolder<String, ReadContext> contextHolder) {
            this.contextHolder = contextHolder;
            return this;
        }

        public ExcelReadParamBuilder batchCount(int batchCount) {
            this.batchCount = batchCount;
            return this;
        }

        public ExcelReadParamBuilder preCheck(boolean preCheck) {
            this.preCheck = preCheck;
            return this;
        }

        public ExcelReadParamBuilder checkHead(boolean checkHead) {
            this.checkHead = checkHead;
            return this;
        }

        public ExcelReadParamBuilder checkCacheRepeat(boolean checkCacheRepeat) {
            this.checkCacheRepeat = checkCacheRepeat;
            return this;
        }

        public ExcelReadParam build() {
            return new ExcelReadParam(key, excelReader, contextHolder, batchCount, preCheck, checkHead, checkCacheRepeat);
        }
    }
}
