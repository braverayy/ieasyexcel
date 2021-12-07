package com.okayhu.framework.ieasyexcel.readv2;

import com.okayhu.framework.ieasyexcel.core.ExcelDataValidator;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author okayhu
 * @date 2021/12/7
 */
@Getter
@Setter
@Builder
public class ExcelReadParam<T> {

    private boolean checkHead;
    private boolean checkCacheRepeat;
    private ExcelReader<T> reader;
    private ExcelDataValidator<T> validator;
}
