package com.leslie.framework.ieasyexcel.read.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.leslie.framework.ieasyexcel.read.ExcelReader;
import com.leslie.framework.ieasyexcel.read.metadata.ExcelReadFlowControl;
import com.leslie.framework.ieasyexcel.read.metadata.ExcelReadValidation;
import com.leslie.framework.ieasyexcel.support.ExcelCommonException;
import com.leslie.framework.ieasyexcel.util.ExcelHeadUtils;
import com.leslie.framework.ieasyexcel.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author leslie
 * @date 2021/3/22
 */
@Slf4j
public class ExcelReadListener<T extends ExcelReadValidation> extends AbstractExcelReadListener<T> {

    protected final String key;

    protected final ExcelReader<T> excelReader;

    protected final ExcelReadFlowControl excelReadFlowControl;

    protected final List<T> dataCache = new ArrayList<>();

    public ExcelReadListener(String key, ExcelReader<T> excelReader) {
        this.key = key;
        this.excelReader = excelReader;
        this.excelReadFlowControl = ExcelReadFlowControl.builder().build();
    }

    public ExcelReadListener(String key, ExcelReader<T> excelReader, ExcelReadFlowControl excelReadFlowControl) {
        this.key = key;
        this.excelReader = excelReader;
        this.excelReadFlowControl = excelReadFlowControl;
    }

    @Override
    public void invoke(T data, AnalysisContext context) {
        super.invoke(data, context);
        log.info("解析数据: {}", JsonUtils.toJsonString(data));

        ExcelReadValidation validation = new ExcelReadValidation();
        if (excelReadFlowControl.isCheckCacheRepeat() && dataCache.contains(data)) {
            validation.setAvailable(false);
            validation.setMsg("数据重复");
        } else if (excelReadFlowControl.isPreCheck()) {
            validation = excelReader.check(data);
        }
        data.setAvailable(validation.getAvailable());
        data.setMsg(validation.getMsg());

        dataCache.add(data);
        if (dataCache.size() >= excelReadFlowControl.getBatchCount()) {
            excelReader.read(dataCache, context);
            dataCache.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        super.doAfterAllAnalysed(context);
        excelReader.read(dataCache, context);
    }

    @Override
    protected String key() {
        return key;
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        boolean isHead = context.readSheetHolder().getHeadRowNumber() - 1 == context.readRowHolder().getRowIndex();
        if (isHead && excelReadFlowControl.isCheckHead()) {
            log.info("Head data: {}", JsonUtils.toJsonString(headMap));

            Map<Integer, String> predefinedHeadMap = new HashMap<>(headMap.size());
            context.currentReadHolder()
                    .excelReadHeadProperty()
                    .getHeadMap()
                    .forEach((index, head) -> predefinedHeadMap.put(index, head.getHeadNameList().get(0)));

            if (!ExcelHeadUtils.same(predefinedHeadMap, headMap)) {
                throw new ExcelCommonException("The Excel header does not match the template!");
            }
        }
    }
}
