package com.leslie.framework.ieasyexcel.read;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author leslie
 * @date 2020/10/16
 */
@Getter
@Setter
@ToString
public class BasedExcelReadModel {

    public static final String DATA_REPEAT = "DATA_REPEAT";

    @JsonIgnore
    private Boolean available;

    @JsonIgnore
    private String msg;
}
