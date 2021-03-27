package com.leslie.framework.ieasyexcel.read.context;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author leslie
 * @date 2021/3/24
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ReadContext {

    private Integer sheetNo;

    private String sheetName;
    /**
     * Gets the total number of rows , data may be inaccurate
     */
    private Integer totalRowNumber;

    private Integer rowIndex;
}
