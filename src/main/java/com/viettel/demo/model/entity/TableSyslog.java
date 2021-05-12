package com.viettel.demo.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Clob;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cat_table_syslog", schema = "IPMS")
public class TableSyslog {

    @Id
    @Column(name = "table_syslog", unique = true, nullable = false)
    private String tableSyslogName;

    @Basic
    @Column(name = "white_list")
    private String whiteList;

    @Basic
    @Column(name = "black_list")
    private String blackList;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fault_level_id")
    private FaultLevel faultLevel;

    @UpdateTimestamp
    @Column(name = "update_time")
    private Date updateTime;

    @CreationTimestamp
    @Column(name = "insert_time")
    private Date insertTime;

    @Basic
    @Column(name = "status")
    private Integer status;
}
