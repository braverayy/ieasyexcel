package com.okayhu.framework.ieasyexcel.context;

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
public class ApplyContext implements Context {

    private Object data;

    private Long index;

    private Long total;
}
