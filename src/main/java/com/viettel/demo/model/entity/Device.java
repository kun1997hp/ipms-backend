package com.viettel.demo.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cat_device", schema = "IPMS", catalog = "ipms_test")
public class Device {

    @Id
    @Column(name = "device_id", unique = true, nullable = false)
    private Integer deviceId;

    @Basic
    @Column(name = "device_code", nullable = false)
    private String deviceCode;

    @Basic
    @Column(name = "device_name")
    private String deviceName;

    @Basic
    @Column(name = "device_ip")
    private String deviceIp;

    @Basic
    @Column(name = "device_ip_full")
    private String deviceIpFull;

    @Basic
    @Column(name = "description")
    private String description;

    @Basic
    @Column(name = "device_type_id")
    private Integer deviceTypeId;

    @Basic
    @Column(name = "network_id")
    private Integer networkId;

    @Basic
    @Column(name = "vendor_id")
    private Integer vendorId;

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
    @Column(name = "serial")
    private String serial;

    @Basic
    @Column(name = "status")
    private Integer status;

    @Basic
    @Column(name = "insert_time")
    private Date insertTime;

    @Basic
    @Column(name = "update_time")
    private Date updateTime;

    @Basic
    @Column(name = "check_ping")
    private Integer checkPing;

    @Basic
    @Column(name = "auto_rescan")
    private Integer autoRescan;

    @Basic
    @Column(name = "sys_description")
    private String sysDescription;

    @Basic
    @Column(name = "sys_version")
    private String sysVersion;

    @Basic
    @Column(name = "sys_series")
    private String sysSeries;

    @Basic
    @Column(name = "snmp_status")
    private Integer snmpStatus;

    @Basic
    @Column(name = "snmp_community")
    private String snmpCommunity;

    @Basic
    @Column(name = "snmp_version")
    private String snmpVersion;

    @Basic
    @Column(name = "bits")
    private Integer bits;

    @Basic
    @Column(name = "table_syslog")
    private String tableSyslog;

    @Basic
    @Column(name = "table_counter")
    private String tableCounter;
}
