package com.leslie.framework.ieasyexcel.read.context;

import com.leslie.framework.ieasyexcel.context.Context;
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
@EqualsAndHashCode(callSuper = false)
public class ReadContext extends Context {

    private Integer sheetNo;

    private String sheetName;
    /**
     * Gets the total number of rows , data may be inaccurate
     */
    private Integer totalRowNumber;

    private Integer rowIndex;
}
