package com.viettel.demo.model.form;

import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Getter
public class DeviceForm {
    private Integer deviceId;
    private String deviceIp;
    @NotBlank(message = "{deviceName.notBlank}")
    private String deviceName;
    private String serial;
    private Integer deviceTypeId;
    private Integer vendorId;
    private Integer networkId;
    private Integer stationId;
    private Integer departmentId;
    private Integer locationId;
    private Integer status;
}
