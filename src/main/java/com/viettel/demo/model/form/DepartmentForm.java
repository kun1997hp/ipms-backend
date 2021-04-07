package com.viettel.demo.model.form;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class DepartmentForm {
    private int departmentId;
    @NotBlank(message = "{departmentCode.notBlank}")
    private String departmentCode;
    private String departmentName;
    private String description;
    private Integer parentId;
    private Integer locationId;
    private Integer departmentLevelId;
    private Integer departmentTypeId;
    private Integer status;
}
