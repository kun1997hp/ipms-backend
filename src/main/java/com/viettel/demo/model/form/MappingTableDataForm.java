package com.viettel.demo.model.form;

import lombok.Getter;

@Getter
public class MappingTableDataForm {
    private Integer networkTypeId;
    private Integer networkClassId;
    private Integer areaId;
    private String module;
    private String tableSyslog;
    private String tableCounter;
    private String tableCounterCustom;
    private Integer status;
}
