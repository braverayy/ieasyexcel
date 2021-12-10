package com.okayhu.framework.ieasyexcel.readv2;

import com.okayhu.framework.ieasyexcel.core.DataValidator;
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
public class ReadParam<T> {

    private boolean checkHead;
    private boolean checkCacheRepeat;
    private DataValidator<T> validator;
}
