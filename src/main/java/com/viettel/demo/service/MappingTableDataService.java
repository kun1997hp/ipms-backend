package com.viettel.demo.service;

import com.viettel.demo.common.DataTable;
import com.viettel.demo.common.message.ErrorMessage;
import com.viettel.demo.exception.customexception.RecordNotFoundException;
import com.viettel.demo.model.entity.*;
import com.viettel.demo.model.form.MappingTableDataForm;
import com.viettel.demo.repository.MappingTableDataRepository;
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

@Service
public class MappingTableDataService {
    @Autowired
    private MappingTableDataRepository mappingTableDataRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private ErrorMessage errorMessage;

    public MappingTableData insertMappingTableData(MappingTableDataForm mappingTableDataForm){
        MappingTableData mappingTableDataFromId;
        if (mappingTableDataForm.getAreaId() != null) mappingTableDataFromId = mappingTableDataRepository.findByNetworkTypeIdAndNetworkClassIdAndAreaId(mappingTableDataForm.getNetworkTypeId(), mappingTableDataForm.getNetworkClassId(), mappingTableDataForm.getAreaId());
        else mappingTableDataFromId = mappingTableDataRepository.findByNetworkTypeIdAndNetworkClassIdAndAreaId(mappingTableDataForm.getNetworkTypeId(), mappingTableDataForm.getNetworkClassId(), null);
        if(mappingTableDataFromId != null) {
            throw new DuplicateKeyException(errorMessage.getDuplicateId());
        }
        mappingTableDataRepository.insertMappingTableData(mappingTableDataForm.getAreaId(), new Date(), mappingTableDataForm.getModule(), mappingTableDataForm.getNetworkClassId(), mappingTableDataForm.getNetworkTypeId(), mappingTableDataForm.getStatus(), mappingTableDataForm.getTableCounter(), mappingTableDataForm.getTableCounterCustom(), mappingTableDataForm.getTableSyslog(), new Date());
        if (mappingTableDataForm.getAreaId() != null) return mappingTableDataRepository.findByNetworkTypeIdAndNetworkClassIdAndAreaId(mappingTableDataForm.getNetworkTypeId(), mappingTableDataForm.getNetworkClassId(), mappingTableDataForm.getAreaId());
        else return mappingTableDataRepository.findByNetworkTypeIdAndNetworkClassIdAndAreaId(mappingTableDataForm.getNetworkTypeId(), mappingTableDataForm.getNetworkClassId(), null);
    }

    public void deleteMappingTableData(String networkTypeId, String networkClassId, String areaId){
        MappingTableData mappingTableDataFromId;
        if (!areaId.equals("")) mappingTableDataFromId = mappingTableDataRepository.findByNetworkTypeIdAndNetworkClassIdAndAreaId(Integer.parseInt(networkTypeId), Integer.parseInt(networkClassId), Integer.parseInt(areaId));
        else mappingTableDataFromId = mappingTableDataRepository.findByNetworkTypeIdAndNetworkClassIdAndAreaId(Integer.parseInt(networkTypeId), Integer.parseInt(networkClassId), null);
        if (mappingTableDataFromId == null) {
            throw new RecordNotFoundException(errorMessage.getRecordNotFound());
        }
        mappingTableDataRepository.deleteMappingTableData(Integer.parseInt(networkTypeId), Integer.parseInt(networkClassId), areaId.equals("") ? null : Integer.parseInt(areaId));
    }

    public void updateMappingTableData(String networkTypeId, String networkClassId, String areaId, MappingTableDataForm mappingTableDataForm){
        MappingTableData mappingTableDataFromId;
        if (!areaId.equals("")) mappingTableDataFromId = mappingTableDataRepository.findByNetworkTypeIdAndNetworkClassIdAndAreaId(Integer.parseInt(networkTypeId), Integer.parseInt(networkClassId), Integer.parseInt(areaId));
        else mappingTableDataFromId = mappingTableDataRepository.findByNetworkTypeIdAndNetworkClassIdAndAreaId(Integer.parseInt(networkTypeId), Integer.parseInt(networkClassId), null);
        if (mappingTableDataFromId == null) {
            throw new RecordNotFoundException(errorMessage.getRecordNotFound());
        }
        mappingTableDataRepository.updateMappingTableData(Integer.parseInt(networkTypeId), Integer.parseInt(networkClassId), areaId.equals("") ? null : Integer.parseInt(areaId), mappingTableDataForm.getAreaId(), mappingTableDataFromId.getInsertTime(), mappingTableDataForm.getModule(), mappingTableDataForm.getNetworkClassId(), mappingTableDataForm.getNetworkTypeId(), mappingTableDataForm.getStatus(), mappingTableDataForm.getTableCounter(), mappingTableDataForm.getTableCounterCustom(), mappingTableDataForm.getTableSyslog(), new Date());
    }

    public MappingTableData findMappingTableDataById(String networkTypeId, String networkClassId, String areaId) {
        MappingTableData mappingTableDataFromId;
        if (!areaId.equals("")) mappingTableDataFromId = mappingTableDataRepository.findByNetworkTypeIdAndNetworkClassIdAndAreaId(Integer.parseInt(networkTypeId), Integer.parseInt(networkClassId), Integer.parseInt(areaId));
        else mappingTableDataFromId = mappingTableDataRepository.findByNetworkTypeIdAndNetworkClassIdAndAreaId(Integer.parseInt(networkTypeId), Integer.parseInt(networkClassId), null);
        if (mappingTableDataFromId == null) {
            throw new RecordNotFoundException(errorMessage.getRecordNotFound());
        }
        return mappingTableDataFromId;
    }

    public DataTable findAllPagingAndSorting(Specification<MappingTableData> specs, Pageable pageable){
        Page<MappingTableData> mappingTableData = mappingTableDataRepository.findAll(specs, pageable);
        return new DataTable(mappingTableData);
    }
}
