package com.viettel.demo.repository;

import com.viettel.demo.model.entity.Device;
import com.viettel.demo.model.entity.TableSyslog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableSyslogRepository extends JpaRepository<TableSyslog, String> {

//    List<Device> findAllByDeviceNameEquals(String deviceName);

    Page<TableSyslog> findAll(Specification<TableSyslog>specs, Pageable pageable);

    Page<TableSyslog> findAll(Pageable pageable);


    TableSyslog getTableSyslogByTableSyslogName(String tableSyslog);
    @Query("select t from TableSyslog t")
//    @Query(value = "select * from CAT_TABLE_SYSLOG", nativeQuery = true)
    Page<TableSyslog> findAllUsingJPQLPagingAndSorting(Pageable pageable);

    @Query("select t from TableSyslog t")
//    @Query(value = "select * from CAT_TABLE_SYSLOG", nativeQuery = true)
    Page<TableSyslog> findAllUsingJPQLPagingAndSorting(Specification<TableSyslog>specs, Pageable pageable);
}
