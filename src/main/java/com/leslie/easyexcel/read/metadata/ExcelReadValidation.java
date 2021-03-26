package com.leslie.easyexcel.read.metadata;

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
public class ExcelReadValidation {

    @JsonIgnore
    private Boolean available;

    @JsonIgnore
    private String msg;
}
