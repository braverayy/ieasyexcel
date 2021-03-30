package com.leslie.framework.ieasyexcel.apply.context;

import com.leslie.framework.ieasyexcel.context.Context;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author leslie
 * @date 2021/3/22
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class ApplyContext extends Context {

    private Object currentData;

    private Long total;

    /**
     * 从0开始
     */
    private Long index;

    public boolean isLast() {
        return total - index == 1;
    }
}
