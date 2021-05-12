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
@Table(name = "cat_location", schema = "IPMS")
public class Location {

    @Id
    @Column(name = "location_id", unique = true, nullable = false)
    private Integer locationId;

    @Basic
    @Column(name = "location_code", nullable = false)
    private String locationCode;

    @Basic
    @Column(name = "location_name")
    private String locationName;

    @Basic
    @Column(name = "description")
    private String description;

    @Basic
    @Column(name = "parent_id")
    private Integer parentId;

    @Basic
    @Column(name = "location_level_id")
    private Integer locationLevelId;

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
//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "locationByLocationId")
//    private Collection<Device> devicesByLocationId;

    @JsonIgnore
    @OneToMany(mappedBy = "locationByAreaId")
    private Collection<MappingTableData> mappingTableDataByLocationId;
}
