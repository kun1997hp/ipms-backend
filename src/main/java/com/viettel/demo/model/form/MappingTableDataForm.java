package com.viettel.demo.model.form;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class MappingTableDataForm {
    @NotNull(message = "{networkTypeId.notNull}")
    private Integer networkTypeId;
    @NotNull(message = "{networkClassId.notNull}")
    private Integer networkClassId;
    private Integer areaId;
    private String module;
    private String tableSyslog;
    private String tableCounter;
    private String tableCounterCustom;
    private Integer status;
}
