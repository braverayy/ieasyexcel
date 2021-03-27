package com.leslie.framework.ieasyexcel.support;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * example
 *
 * @author leslie
 * @date 2021/3/23
 */
@Getter
@Setter
@MappedSuperclass
public class ExcelBasedReadRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "excel_type")
    private String excelType;

    @Column(name = "excel_name")
    private String excelName;

    @Column(name = "excel_url")
    private String excelUrl;

    @Column(name = "excel_size_b")
    private Long excelSizeB;

    @Column(name = "op_user_id")
    private Long opUserId;

    @Column(name = "op_uname")
    private String opUname;

    @Column(name = "status")
    private String status;
}
