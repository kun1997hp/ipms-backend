package com.viettel.demo.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Clob;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SL_CORE_UNKNOWN", schema = "IPMS")
public class SyslogBase {
    @Id
    @Column(name = "ALARM_ID")
    private Integer alarmId;
    @Basic
    @Column(name = "DEVICE_ID")
    private Integer deviceId;
    @Basic
    @Column(name = "DEVICE_NAME")
    private String deviceName;
    @Basic
    @Column(name = "DEVICE_IP")
    private String deviceIp;
    @Basic
    @Column(name = "DEVICE_TYPE_ID")
    private Integer deviceTypeId;
    @Basic
    @Column(name = "DEVICE_TYPE_NAME")
    private String deviceTypeName;
    @Basic
    @Column(name = "VENDOR_ID")
    private Integer vendorId;
    @Basic
    @Column(name = "VENDOR_CODE")
    private String vendorCode;
    @Basic
    @Column(name = "NETWORK_CLASS_ID")
    private Integer networkClassId;
    @Basic
    @Column(name = "NETWORK_CLASS_CODE")
    private String networkClassCode;
    @Basic
    @Column(name = "NETWORK_TYPE_ID")
    private Integer networkTypeId;
    @Basic
    @Column(name = "NETWORK_TYPE_CODE")
    private String networkTypeCode;
    @Basic
    @Column(name = "START_TIME")
    private Date startTime;
    @Basic
    @Column(name = "INSERT_TIME")
    private Date insertTime;
    @Basic
    @Column(name = "MESSAGE")
    private String message;
    @Basic
    @Column(name = "FAULT_LEVEL_ID")
    private Integer faultLevelId;
    @Basic
    @Column(name = "ADAPTER_NAME")
    private String adapterName;
    @Basic
    @Column(name = "AREA_ID")
    private Integer areaId;
    @Basic
    @Column(name = "AREA_NAME")
    private String areaName;
    @Basic
    @Column(name = "PROVINCE_ID")
    private Integer provinceId;
    @Basic
    @Column(name = "PROVINCE_NAME")
    private String provinceName;
    @Basic
    @Column(name = "LOCATION_ID")
    private Integer locationId;
}
