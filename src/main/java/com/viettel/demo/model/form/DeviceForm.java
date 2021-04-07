package com.viettel.demo.model.form;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class DeviceForm {
    private Integer deviceId;
    @NotBlank(message = "{deviceCode.notBlank}")
    private String deviceCode;
    private String deviceName;
    private String deviceIp;
    private String deviceIpFull;
    private String description;
    private Integer deviceTypeId;
    private Integer networkId;
    private Integer vendorId;
    private Integer stationId;
    private Integer departmentId;
    private Integer locationId;
    private String serial;
    private Integer status;
    private Integer checkPing;
    private Integer autoRescan;
    private String sysDescription;
    private String sysVersion;
    private String sysSeries;
    private Integer snmpStatus;
    private String snmpCommunity;
    private String snmpVersion;
    private Integer bits;
    private String tableSyslog;
    private String tableCounter;
}
