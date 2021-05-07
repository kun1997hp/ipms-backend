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
@Table(name = "cat_vendor", schema = "IPMS")
public class Vendor {

    @Id
    @Column(name = "vendor_id", unique = true, nullable = false)
    private Integer vendorId;

    @Basic
    @Column(name = "vendor_code")
    private String vendorCode;

    @Basic
    @Column(name = "vendor_name")
    private String vendorName;

    @Basic
    @Column(name = "insert_time")
    private Date insertTime;

    @Basic
    @Column(name = "update_time")
    private Date updateTime;

//    @JsonIgnore
//    @Fetch(FetchMode.SELECT)
//    @LazyCollection(LazyCollectionOption.EXTRA)
//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "vendorByVendorId")
//    private Collection<Device> devicesByVendorId;
}
