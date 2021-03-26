package com.leslie.easyexcel.read;

import com.alibaba.excel.context.AnalysisContext;
import com.leslie.easyexcel.read.metadata.ExcelReadValidation;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author leslie
 * @date 2021/3/22
 */
public interface ExcelReader<T> {

    Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

    void read(List<T> excelDataList, AnalysisContext context);

    default ExcelReadValidation check(T excelData) {
        ExcelReadValidation validation = new ExcelReadValidation();
        Set<ConstraintViolation<T>> validate = VALIDATOR.validate(excelData);
        if (validate != null && !validate.isEmpty()) {
            List<String> failureMsg = validate.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
            validation.setAvailable(false);
            validation.setMsg(String.join(";", failureMsg));
        } else {
            validation.setAvailable(true);
        }
        return validation;
    }
}
