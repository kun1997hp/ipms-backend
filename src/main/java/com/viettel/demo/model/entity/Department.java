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
import java.util.Collection;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cat_department", schema = "IPMS")
public class Department {

    @Id
    @Column(name = "department_id", unique = true, nullable = false)
    private Integer departmentId;

    @Basic
    @Column(name = "dept_code", nullable = false)
    private String departmentCode;

    @Basic
    @Column(name = "dept_name")
    private String departmentName;

    @Basic
    @Column(name = "description")
    private String description;

    @Basic
    @Column(name = "parent_id")
    private Integer parentId;

    @Basic
    @Column(name = "location_id")
    private Integer locationId;

    @Basic
    @Column(name = "department_level_id")
    private Integer departmentLevelId;

    @Basic
    @Column(name = "department_type_id")
    private Integer departmentTypeId;

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
//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "departmentByDepartmentId")
//    private Collection<Device> devicesByDepartmentId;
}
