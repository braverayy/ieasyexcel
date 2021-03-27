package com.leslie.framework.ieasyexcel.support;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * example
 *
 * @author leslie
 * @date 2020/12/29
 */
@Getter
@Setter
@MappedSuperclass
public class ExcelBasedReadData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "excel_record_id")
    private Long excelRecordId;

    @Column(name = "row_value")
    private String rowValue;

    @Column(name = "status")
    private String status;

    @Column(name = "msg")
    private String msg;
}
