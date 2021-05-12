package com.viettel.demo.model.form;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
public class SyslogBaseForm {
    @NotBlank(message = "{alarmId.notBlank}")
    private Long alarmId;
    private String deviceIp;
    private Date startTime;
    private Long deviceId;
    private String deviceName;
    private Long deviceTypeId;
    private String deviceTypeName;
    private Long vendorId;
    private String vendorCode;
    private Long networkClassId;
    private String networkClassCode;
    private Long networkTypeId;
    private String networkTypeCode;
    private Date insertTime;
    private String message;
    private Long faultLevelId;
    private String adapterName;
    private Long areaId;
    private String areaName;
    private Long provinceId;
    private String provinceName;
    private Long locationId;
}
