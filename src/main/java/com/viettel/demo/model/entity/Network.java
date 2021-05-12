package com.viettel.demo.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cat_network", schema = "IPMS")
public class Network {

    @Id
    @Column(name = "network_id", unique = true, nullable = false)
    private Integer networkId;

    @Basic
    @Column(name = "network_code", nullable = false)
    private String networkCode;

    @Basic
    @Column(name = "network_name")
    private String networkName;

    @Basic
    @Column(name = "parent_id")
    private Integer parentId;

    @Basic
    @Column(name = "status")
    private Integer status;

    @Basic
    @Column(name = "insert_time")
    private Date insertTime;

    @Basic
    @Column(name = "update_time")
    private Date updateTime;

//    @JsonIgnore
//    @Fetch(FetchMode.SELECT)
//    @LazyCollection(LazyCollectionOption.EXTRA)
//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "networkByNetworkId")
//    private Collection<Device> devicesByNetworkId;

    @JsonIgnore
    @OneToMany(mappedBy = "networkByNetworkTypeId")
    private Collection<MappingTableData> mappingTableDataByNetworkTypeId;

    @JsonIgnore
    @OneToMany(mappedBy = "networkByNetworkClassId")
    private Collection<MappingTableData> mappingTableDataByNetworkClassId;
}
