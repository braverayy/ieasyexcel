package com.okayhu.framework.ieasyexcel.readv2;

import com.alibaba.excel.context.AnalysisContext;
import com.okayhu.framework.ieasyexcel.core.ValidationResult;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author okayhu
 * @date 2021/12/7
 */
@Getter
@Setter
public class RowContext<T> implements Serializable {

    private static final long serialVersionUID = 5372269490516565020L;

    private T rowData;
    private ValidationResult validation;
    private AnalysisContext analysisContext;

    public RowContext(T rowData, ValidationResult validation, AnalysisContext analysisContext) {
        this.rowData = rowData;
        this.validation = validation;
        this.analysisContext = analysisContext;
    }
}
