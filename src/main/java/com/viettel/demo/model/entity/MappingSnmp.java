package com.viettel.demo.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Subselect;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cat_mapping_snmp", schema = "IPMS", catalog = "ipms_test")
@Subselect("select row_number() over (order by insert_time) as temp_id , t.* from cat_mapping_snmp t")
public class MappingSnmp implements Serializable {

    @Id
    private Long tempId;

    @Column(name = "network_type_id")
    private Integer networkTypeId;
    @ManyToOne
    @JoinColumn(name = "network_type_id", referencedColumnName = "network_id", insertable = false, updatable = false)
    private Network networkByNetworkTypeId;

    @Column(name = "network_class_id")
    private Integer networkClassId;
    @ManyToOne
    @JoinColumn(name = "network_class_id", referencedColumnName = "network_id", insertable = false, updatable = false)
    private Network networkByNetworkClassId;

    @Column(name = "vendor_id")
    private Integer vendorId;
    @ManyToOne
    @JoinColumn(name = "vendor_id", referencedColumnName = "vendor_id", insertable = false, updatable = false)
    private Vendor vendorByVendorId;

    @Column(name = "device_type_id")
    private Integer deviceTypeId;
    @ManyToOne
    @JoinColumn(name = "device_type_id", referencedColumnName = "device_type_id", insertable = false, updatable = false)
    private DeviceType deviceTypeByDeviceTypeId;

    @Column(name = "area_id")
    private Integer areaId;
    @ManyToOne
    @JoinColumn(name = "area_id", referencedColumnName = "location_id", insertable = false, updatable = false)
    private Location locationByAreaId;

    @Basic
    @Column(name = "snmp_community")
    private String snmpCommunity;

    @Basic
    @Column(name = "snmp_version")
    private String snmpVersion;

    @Basic
    @Column(name = "status")
    private Integer status;

    @Basic
    @Column(name = "insert_time")
    private Date insertTime;

    @Basic
    @Column(name = "update_time")
    private Date updateTime;
}
