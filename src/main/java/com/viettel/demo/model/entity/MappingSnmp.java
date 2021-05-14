package com.viettel.demo.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cat_mapping_snmp", schema = "IPMS", catalog = "ipms_test")
public class MappingSnmp implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mappingTableDataSeq")
    @SequenceGenerator(name = "mappingTableDataSeq", sequenceName = "cat_mapping_table_data_seq", allocationSize = 1,initialValue = 1)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "network_type_id", referencedColumnName = "network_id")
    private Network networkByNetworkTypeId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "network_class_id", referencedColumnName = "network_id")
    private Network networkByNetworkClassId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vendor_id", referencedColumnName = "vendor_id")
    private Vendor vendorByVendorId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "device_type_id", referencedColumnName = "device_type_id")
    private DeviceType deviceTypeByDeviceTypeId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "area_id", referencedColumnName = "location_id")
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
