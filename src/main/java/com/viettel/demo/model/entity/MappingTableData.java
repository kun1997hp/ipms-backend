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
@IdClass(MappingTableDataId.class)
@Table(name = "cat_mapping_table_data", schema = "IPMS", catalog = "ipms_test")
public class MappingTableData implements Serializable {

    /*@Id
    @ManyToOne
    @JoinColumn(name = "network_type_id", referencedColumnName = "network_id")
    private Network networkByNetworkTypeId;*/

    @Id
    @ManyToOne
    @JoinColumn(name = "network_class_id", referencedColumnName = "network_id")
    private Network networkByNetworkClassId;

    @Id
    @ManyToOne
    @JoinColumn(name = "area_id", referencedColumnName = "location_id")
    private Location locationByAreaId;

    @Basic
    @Column(name = "module")
    private String module;

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
    @Column(name = "status")
    private Integer status;

    @Basic
    @Column(name = "insert_time")
    private Date insertTime;

    @Basic
    @Column(name = "update_time")
    private Date updateTime;
}
