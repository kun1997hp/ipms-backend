package com.viettel.demo.model.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Time;
import java.util.Objects;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ALARM_EVENT_CURRENT", schema = "IPMS")
public class AlarmEvent {
    @Id
    @Column(name = "ALARM_ID")
    private Integer alarmId;
    @Basic
    @Column(name = "ALARM_SEQUENCE")
    private String alarmSequence;
    @Basic
    @Column(name = "FAULT_NAME")
    private String faultName;
    @Basic
    @Column(name = "START_TIME")
    private Time startTime;
    @Basic
    @Column(name = "END_TIME")
    private Time endTime;
    @Basic
    @Column(name = "CONTENT")
    private String content;
    @Basic
    @Column(name = "DEVICE_ID")
    private Integer deviceId;
    @Basic
    @Column(name = "DEVICE_IP")
    private String deviceIp;
    @Basic
    @Column(name = "DEVICE_CODE")
    private String deviceCode;
    @Basic
    @Column(name = "DEVICE_NAME")
    private String deviceName;
    @Basic
    @Column(name = "INTERFACE_NAME")
    private String interfaceName;
    @Basic
    @Column(name = "INTERFACE_GROUP_ID")
    private Integer interfaceGroupId;
    @Basic
    @Column(name = "VENDOR_ID")
    private Integer vendorId;
    @Basic
    @Column(name = "VENDOR_CODE")
    private String vendorCode;
    @Basic
    @Column(name = "DEVICE_TYPE_ID")
    private Integer deviceTypeId;
    @Basic
    @Column(name = "DEVICE_TYPE_NAME")
    private String deviceTypeName;
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
    @Basic
    @Column(name = "LOG_SERVER_ID")
    private String logServerId;
    @Basic
    @Column(name = "ALARM_STATUS")
    private Integer alarmStatus;
    @Basic
    @Column(name = "UPDATE_TIME")
    private Time updateTime;
    @Basic
    @Column(name = "INSERT_TIME")
    private Time insertTime;
    @Basic
    @Column(name = "CREATE_TIME")
    private Time createTime;
}
