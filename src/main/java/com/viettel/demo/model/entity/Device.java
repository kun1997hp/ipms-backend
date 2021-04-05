package com.viettel.demo.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "device", schema = "IPMS", catalog = "ipms_test")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "devicesequence")
    @SequenceGenerator(name = "devicesequence", sequenceName = "device_seq", allocationSize = 1,initialValue = 1)
    @Column(name = "device_id")
    private int deviceId;

    @Basic
    @Column(name = "device_ip")
    private String deviceIp;

    @Basic
    @Column(name = "device_name")
    private String deviceName;

    @Basic
    @Column(name = "serial")
    private String serial;

    @Basic
    @Column(name = "device_type_id")
    private Integer deviceTypeId;

    @Basic
    @Column(name = "vendor_id")
    private Integer vendorId;

    @Basic
    @Column(name = "network_id")
    private Integer networkId;

    @Basic
    @Column(name = "station_id")
    private Integer stationId;

    @Basic
    @Column(name = "department_id")
    private Integer departmentId;

    @Basic
    @Column(name = "location_id")
    private Integer locationId;

    @Basic
    @Column(name = "status")
    private Integer status;

    @Basic
    @Column(name = "insert_time")
    private Timestamp insertTime;

    @Basic
    @Column(name = "update_time")
    private Timestamp updateTime;

}
