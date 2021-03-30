package com.leslie.framework.ieasyexcel.read.metadata;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * @author leslie
 * @date 2021/3/30
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@ExcelIgnoreUnannotated
public class UserExcel extends ExcelReadValidation{

    @ExcelProperty("用户名")
    @NotBlank(message = "用户名不能为空")
    private String username;

    @ExcelProperty("联系电话")
    private String mobile;

    @ExcelProperty("密码")
    @NotBlank(message = "密码不能为空")
    private String pwd;

    @ExcelProperty("真实姓名")
    private String name;
}
