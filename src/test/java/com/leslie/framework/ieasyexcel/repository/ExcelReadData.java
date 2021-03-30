package com.leslie.framework.ieasyexcel.repository;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author leslie
 * @date 2021/3/30
 */
@Getter
@Setter
@ToString
public class ExcelReadData {

    private Long id;

    private Long excelRecordId;

    private String rowValue;

    private String status;

    private String msg;

    public ExcelReadData(Long id,String rowValue) {
        this.id = id;
        this.excelRecordId = 1L;
        this.rowValue = rowValue;
    }
}
