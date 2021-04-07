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
@Table(name = "cat_location", schema = "IPMS", catalog = "ipms_test")
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

}
