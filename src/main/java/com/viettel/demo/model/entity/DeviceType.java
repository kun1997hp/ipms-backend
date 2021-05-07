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
@Table(name = "cat_device_type", schema = "IPMS")
public class DeviceType {
    @Id
    @Column(name = "device_type_id", unique = true, nullable = false)
    private Integer deviceTypeId;

    @Basic
    @Column(name = "device_type_code", nullable = false)
    private String deviceTypeCode;

    @Basic
    @Column(name = "device_type_name")
    private String deviceTypeName;

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
//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "deviceTypeByDeviceTypeId")
//    @LazyCollection(LazyCollectionOption.EXTRA)
//    private List<Device> devicesByDeviceTypeId = new ArrayList<>(0);
}
