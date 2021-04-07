package com.viettel.demo.model.form;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Getter
public class LocationForm {
    private int locationId;
    @NotBlank(message = "{locationCode.notBlank}")
    private String locationCode;
    private String locationName;
    private String description;
    private Integer parentId;
    private Integer locationLevelId;
    private Integer status;
}
