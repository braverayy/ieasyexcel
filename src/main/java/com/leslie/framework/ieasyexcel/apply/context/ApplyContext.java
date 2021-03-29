package com.leslie.framework.ieasyexcel.apply.context;

import com.leslie.framework.ieasyexcel.context.Context;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.RoundingMode;

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

    public String progress() {
        BigDecimal indexBdl = new BigDecimal(String.valueOf(index * 100));
        BigDecimal totalBdl = new BigDecimal(String.valueOf(total));
        BigDecimal result = indexBdl.divide(totalBdl, 2, RoundingMode.HALF_DOWN);
        return result.toString();
    }

    public boolean isLast() {
        return total - index == 1;
    }
}
