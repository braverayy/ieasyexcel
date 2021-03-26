package com.leslie.easyexcel.read.metadata;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author leslie
 * @date 2021/3/22
 */
@Getter
@Setter
@Builder
@ToString
public class ExcelReadFlowControl {

    @Builder.Default
    private int batchCount = 100;

    @Builder.Default
    private boolean preCheck = true;

    @Builder.Default
    private boolean checkHead = true;

    @Builder.Default
    private boolean checkCacheRepeat = true;
}
