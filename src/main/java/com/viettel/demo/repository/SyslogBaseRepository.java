package com.viettel.demo.repository;

import com.viettel.demo.model.entity.SyslogBase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SyslogBaseRepository extends JpaRepository<SyslogBase, Integer> {

    Page<SyslogBase> findAll(Specification<SyslogBase> specs, Pageable pageable);

    Page<SyslogBase> findAll(Pageable pageable);


    SyslogBase getSyslogBaseByAlarmId(Integer alarmId);
    @Query("select t from SyslogBase t")
    Page<SyslogBase> findAllUsingJPQLPagingAndSorting(Pageable pageable);

    @Query("select t from SyslogBase t")
//    @Query(value = "select * from CAT_TABLE_SYSLOG", nativeQuery = true)
    Page<SyslogBase> findAllUsingJPQLPagingAndSorting(Specification<SyslogBase>specs, Pageable pageable);
}
