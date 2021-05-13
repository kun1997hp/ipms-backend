package com.viettel.demo.model.form;

import lombok.Getter;

@Getter
public class MappingSnmpForm {
    private Integer networkTypeId;
    private Integer networkClassId;
    private Integer vendorId;
    private Integer deviceTypeId;
    private Integer areaId;
    private String snmpCommunity;
    private String snmpVersion;
    private Integer status;
}
