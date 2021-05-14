package com.viettel.demo.model.form;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class MappingSnmpForm {
    @NotNull(message = "{networkTypeId.notNull}")
    private Integer networkTypeId;
    @NotNull(message = "{networkClassId.notNull}")
    private Integer networkClassId;
    @NotNull(message = "{vendorId.notNull}")
    private Integer vendorId;
    @NotNull(message = "{deviceTypeId.notNull}")
    private Integer deviceTypeId;
    private Integer areaId;
    private String snmpCommunity;
    private String snmpVersion;
    private Integer status;
}
