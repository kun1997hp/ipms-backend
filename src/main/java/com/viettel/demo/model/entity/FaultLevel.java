package com.viettel.demo.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cat_fault_level", schema = "IPMS")
public class FaultLevel {
    @Id
    @Column(name = "fault_level_id", unique = true, nullable = false)
    private Integer faultLevelId;

    @Basic
    @Column(name = "fault_level_name")
    private String faultLevelName;
}
