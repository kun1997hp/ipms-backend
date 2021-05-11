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
@Table(name = "cat_mapping_table_data", schema = "IPMS", catalog = "ipms_test")
@Subselect("select row_number() over (order by insert_time) as temp_id , t.* from cat_mapping_table_data t")
public class MappingTableData implements Serializable {

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

    @Column(name = "area_id")
    private Integer areaId;
    @ManyToOne
    @JoinColumn(name = "area_id", referencedColumnName = "location_id", insertable = false, updatable = false)
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
