package com.leslie.framework.ieasyexcel.context;

import lombok.*;

/**
 * @author leslie
 * @date 2021/3/24
 */
@Getter
@Setter
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ReadContext extends Context {

    private Integer sheetNo;

    private String sheetName;

    private Integer rowIndex;
    /**
     * data may be inaccurate
     */
    private Integer rowTotal;
}
