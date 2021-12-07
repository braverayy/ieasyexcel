package com.okayhu.framework.ieasyexcel.readv2;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ConverterUtils;
import com.okayhu.framework.ieasyexcel.core.ExcelDataValidator;
import com.okayhu.framework.ieasyexcel.core.ValidationResult;
import com.okayhu.framework.ieasyexcel.util.ExcelHeadUtils;
import com.okayhu.framework.ieasyexcel.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * @author okayhu
 * @date 2021/12/7
 */
@Slf4j
public abstract class AbstractReadListener<T> implements ReadListener<T> {

    private final ExcelReadParam<T> readParam;

    public AbstractReadListener(ExcelReadParam<T> readParam) {
        this.readParam = readParam;
    }

    @Override
    public void invoke(T rowData, AnalysisContext analysisContext) {
        log.info("Row data: {}", JsonUtils.toJsonString(rowData));
        // set to read context

        // validate row data
        ValidationResult validationResult = validate(rowData);

        // do read
        readParam.getReader().read(rowData, validationResult, analysisContext);
    }

    @Override
    public void invokeHead(Map<Integer, ReadCellData<?>> headMap, AnalysisContext context) {
        Map<Integer, String> headStringMap = ConverterUtils.convertToStringMap(headMap, context);
        boolean isHead = context.readSheetHolder().getHeadRowNumber() - 1 == context.readRowHolder().getRowIndex();
        if (isHead && readParam.isCheckHead()) {
            log.info("Head data: {}", JsonUtils.toJsonString(headMap));

            Map<Integer, String> templatedHeadMap = new HashMap<>(headMap.size());
            context.currentReadHolder()
                    .excelReadHeadProperty()
                    .getHeadMap()
                    .forEach((index, head) -> templatedHeadMap.put(index, head.getHeadNameList().get(0)));

            if (!ExcelHeadUtils.same(templatedHeadMap, headStringMap)) {
                throw new ExcelHeadParseException("Excel header does not match the template!");
            }
        }
    }

    private ValidationResult validate(T rowData) {
        ExcelDataValidator<T> validator = readParam.getValidator();
        return validator == null ? ValidationResult.success() : validator.validate(rowData);
    }
}
