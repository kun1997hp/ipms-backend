package com.viettel.demo.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cat_station", schema = "IPMS", catalog = "ipms_test")
public class Station {

    @Id
    @Column(name = "station_id", unique = true, nullable = false)
    private Integer stationId;

    @Basic
    @Column(name = "station_code", nullable = false)
    private String stationCode;

    @Basic
    @Column(name = "station_name")
    private String stationName;

    @Basic
    @Column(name = "station_type_id")
    private Integer stationTypeId;

    @Basic
    @Column(name = "install_date")
    private Date installDate;

    @Basic
    @Column(name = "department_id")
    private Integer departmentId;

    @Basic
    @Column(name = "location_id")
    private Integer locationId;

    @Basic
    @Column(name = "address")
    private String address;

    @Basic
    @Column(name = "description")
    private String description;

    @Basic
    @Column(name = "position")
    private Integer position;

    @Basic
    @Column(name = "gps_x")
    private Integer gpsX;

    @Basic
    @Column(name = "gps_y")
    private Integer gpsY;

    @Basic
    @Column(name = "home_owner")
    private String homeOwner;

    @Basic
    @Column(name = "telephone")
    private String telephone;

    @Basic
    @Column(name = "status")
    private Integer status;

    @Basic
    @Column(name = "insert_time")
    private Date insertTime;

    @Basic
    @Column(name = "update_time")
    private Date updateTime;

    @JsonIgnore
    @OneToMany(mappedBy = "stationByStationId")
    private Collection<Device> devicesByStationId;
}
