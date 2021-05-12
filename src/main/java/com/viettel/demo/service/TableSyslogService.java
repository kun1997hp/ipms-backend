package com.viettel.demo.service;

import com.viettel.demo.common.DataTable;
import com.viettel.demo.common.message.ErrorMessage;
import com.viettel.demo.exception.customexception.RecordNotFoundException;
import com.viettel.demo.model.entity.*;
import com.viettel.demo.model.form.TableSyslogForm;
import com.viettel.demo.model.form.TableSyslogForm;
import com.viettel.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.util.Date;
import java.util.Optional;

@Service
public class TableSyslogService {
    @Autowired
    private TableSyslogRepository tableSyslogRepository;

    @Autowired
    private FaultLevelRepository faultLevelRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private ErrorMessage errorMessage;

    public TableSyslog insertTableSyslog(TableSyslogForm tableSyslogForm){
        Optional<TableSyslog> tableSyslogFromId = tableSyslogRepository.findById(tableSyslogForm.getTableSyslogName());
        if(tableSyslogFromId.isPresent()) {
            throw new DuplicateKeyException(errorMessage.getDuplicateId());
        }
        TableSyslog tableSyslog = TableSyslog.builder().tableSyslogName(tableSyslogForm.getTableSyslogName())
                .whiteList(tableSyslogForm.getWhiteList())
                .blackList(tableSyslogForm.getBlackList())
                .faultLevel(faultLevelRepository.getFaultLevelByFaultLevelId(tableSyslogForm.getFaultLevelId()))
                .insertTime(new Date())
                .updateTime(new Date())
                .status(tableSyslogForm.getStatus())
                .build();
        return tableSyslogRepository.save(tableSyslog);
    }

    public void deleteTableSyslog(String tableSyslogName){
        Optional<TableSyslog> tableSyslogFromId = tableSyslogRepository.findById(tableSyslogName);
        if(!tableSyslogFromId.isPresent()) {
            throw new RecordNotFoundException(errorMessage.getRecordNotFound());
        }
        tableSyslogRepository.delete(tableSyslogFromId.get());
    }

    public void updateTableSyslog(String tableSyslogName, TableSyslogForm tableSyslogForm){
        Optional<TableSyslog> tableSyslogFromId = tableSyslogRepository.findById(tableSyslogName);
        if(!tableSyslogFromId.isPresent()) {
            throw new RecordNotFoundException(errorMessage.getRecordNotFound());
        }
        TableSyslog tableSyslog = TableSyslog.builder().tableSyslogName(tableSyslogName)
                .whiteList(tableSyslogForm.getWhiteList())
                .blackList(tableSyslogForm.getBlackList())
                .faultLevel(faultLevelRepository.getFaultLevelByFaultLevelId(tableSyslogForm.getFaultLevelId()))
                .insertTime(new Date())
                .updateTime(new Date())
                .status(tableSyslogForm.getStatus())
                .build();
        tableSyslogRepository.save(tableSyslog);
    }

    public TableSyslog findTableSyslogById(String tableSyslogName) {
        TableSyslog tableSyslog= tableSyslogRepository.getTableSyslogByTableSyslogName(tableSyslogName);
        if (tableSyslog == null) {
            throw new RecordNotFoundException(errorMessage.getRecordNotFound());
        }
        return tableSyslog;
    }
    public DataTable findAllPagingAndSorting(Specification<TableSyslog> specs, Pageable pageable){
        Page<TableSyslog> tableSyslogs = tableSyslogRepository.findAll(specs, pageable);
        return new DataTable(tableSyslogs);
    }
    public DataTable findAllUsingJPQLPagingAndSorting(Pageable pageable){
        Page<TableSyslog> tableSyslogs = tableSyslogRepository.findAllUsingJPQLPagingAndSorting(pageable);
        return new DataTable(tableSyslogs);
    }

}
