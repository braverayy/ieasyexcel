package com.leslie.framework.ieasyexcel.context;

import lombok.*;

/**
 * @author leslie
 * @date 2021/3/22
 */
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
public class ApplyContext extends Context {

    private Object data;

    private Long total;

    private Long index;
}
