package com.viettel.demo.model.form;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class DeviceForm {
    private Integer deviceId;
    @NotBlank(message = "{deviceCode.notBlank}")
    private String deviceCode;
    private String deviceName;
    private String deviceIp;
    private String deviceIpFull;
    private String description;
    @NotNull(message = "{deviceTypeId.notNull}")
    private Integer deviceTypeId;
    @NotNull(message = "{networkId.notNull}")
    private Integer networkId;
    @NotNull(message = "{vendorId.notNull}")
    private Integer vendorId;
    @NotNull(message = "{stationId.notNull}")
    private Integer stationId;
    @NotNull(message = "{departmentId.notNull}")
    private Integer departmentId;
    @NotNull(message = "{locationId.notNull}")
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
