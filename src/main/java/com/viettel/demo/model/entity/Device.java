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
@Table(name = "cat_device", schema = "IPMS")
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "device_type_id", referencedColumnName = "device_type_id")
    private DeviceType deviceTypeByDeviceTypeId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "network_id", referencedColumnName = "network_id")
    private Network networkByNetworkId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vendor_id", referencedColumnName = "vendor_id")
    private Vendor vendorByVendorId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "station_id", referencedColumnName = "station_id")
    private Station stationByStationId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id", referencedColumnName = "department_id")
    private Department departmentByDepartmentId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "location_id", referencedColumnName = "location_id")
    private Location locationByLocationId;

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

    @Basic
    @Column(name = "table_counter_custom")
    private String tableCounterCustom;

    @Basic
    @Column(name = "module")
    private String module;

    @Basic
    @Column(name = "custom_scrip")
    private String customScrip;
}
