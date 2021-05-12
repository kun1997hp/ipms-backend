package com.viettel.demo.model.form;

import javax.validation.constraints.NotBlank;
import java.util.Date;

public class AlarmEventForm
{
    @NotBlank(message = "{alarmId.notBlank}")
    private Integer alarmId;
    private String alarmSequence;
    private String faultName;
    private Date startTime;
    private Date endTime;
    private String content;
    private Integer deviceId;
    private String deviceIp;
    private String deviceCode;
    private String deviceName;
    private String interfaceName;
    private Integer interfaceGroupId;
    private Integer vendorId;
    private String vendorCode;
    private Integer deviceTypeId;
    private String deviceTypeName;
    private Integer networkClassId;
    private String networkClassCode;
    private Integer networkTypeId;
    private String networkTypeCode;
    private Integer areaId;
    private String areaName;
    private Integer provinceId;
    private String provinceName;
    private Integer locationId;
    private String logServerId;
    private Integer alarmStatus;
    private Date updateTime;
    private Date insertTime;
    private Date createTime;
}

