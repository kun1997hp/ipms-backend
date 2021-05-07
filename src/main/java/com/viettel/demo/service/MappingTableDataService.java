package com.viettel.demo.service;

import com.viettel.demo.common.DataTable;
import com.viettel.demo.common.message.ErrorMessage;
import com.viettel.demo.exception.customexception.RecordNotFoundException;
import com.viettel.demo.model.entity.*;
import com.viettel.demo.model.form.MappingTableDataForm;
import com.viettel.demo.repository.LocationRepository;
import com.viettel.demo.repository.MappingTableDataRepository;
import com.viettel.demo.repository.NetworkRepository;
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
public class MappingTableDataService {
    @Autowired
    private MappingTableDataRepository mappingTableDataRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private ErrorMessage errorMessage;

    @Autowired
    private NetworkRepository networkRepository;

    @Autowired
    private LocationRepository locationRepository;

    public MappingTableData insertMappingTableData(MappingTableDataForm mappingTableDataForm){
        Optional<MappingTableData> mappingTableDataFromId = mappingTableDataRepository.findById(new MappingTableDataId(mappingTableDataForm.getNetworkClassId(), mappingTableDataForm.getAreaId()));
        if(mappingTableDataFromId.isPresent()) {
            throw new DuplicateKeyException(errorMessage.getDuplicateId());
        }
        MappingTableData mappingTableData = MappingTableData.builder()
//                .networkByNetworkTypeId(networkRepository.getNetworkByNetworkId(mappingTableDataForm.getNetworkTypeId()))
                .networkByNetworkClassId(networkRepository.getNetworkByNetworkId(mappingTableDataForm.getNetworkClassId()))
                .locationByAreaId(locationRepository.getLocationByLocationId(mappingTableDataForm.getAreaId()))
                .module(mappingTableDataForm.getModule())
                .tableSyslog(mappingTableDataForm.getTableSyslog())
                .tableCounter(mappingTableDataForm.getTableCounter())
                .tableCounterCustom(mappingTableDataForm.getTableCounterCustom())
                .insertTime(new Date())
                .updateTime(new Date())
                .status(mappingTableDataForm.getStatus())
                .build();
        return mappingTableDataRepository.save(mappingTableData);
    }

    public void deleteMappingTableData(String networkTypeId, String networkClassId, String areaId){
        Optional<MappingTableData> mappingTableDataFromId = mappingTableDataRepository.findById(new MappingTableDataId( Integer.parseInt(networkClassId), Integer.parseInt(areaId)));
        if(!mappingTableDataFromId.isPresent()) {
            throw new RecordNotFoundException(errorMessage.getRecordNotFound());
        }
        mappingTableDataRepository.delete(mappingTableDataFromId.get());
    }

    public void updateMappingTableData(String networkTypeId, String networkClassId, String areaId, MappingTableDataForm mappingTableDataForm){
        Optional<MappingTableData> mappingTableDataFromId = mappingTableDataRepository.findById(new MappingTableDataId( Integer.parseInt(networkClassId), Integer.parseInt(areaId)));
        if(!mappingTableDataFromId.isPresent()) {
            throw new RecordNotFoundException(errorMessage.getRecordNotFound());
        }
        MappingTableData mappingTableData = MappingTableData.builder()
//                .networkByNetworkTypeId(networkRepository.getNetworkByNetworkId(mappingTableDataForm.getNetworkTypeId()))
                .networkByNetworkClassId(networkRepository.getNetworkByNetworkId(mappingTableDataForm.getNetworkClassId()))
                .locationByAreaId(locationRepository.getLocationByLocationId(mappingTableDataForm.getAreaId()))
                .module(mappingTableDataForm.getModule())
                .tableSyslog(mappingTableDataForm.getTableSyslog())
                .tableCounter(mappingTableDataForm.getTableCounter())
                .tableCounterCustom(mappingTableDataForm.getTableCounterCustom())
                .insertTime(new Date())
                .updateTime(new Date())
                .status(mappingTableDataForm.getStatus())
                .build();
        mappingTableDataRepository.save(mappingTableData);
    }

    public MappingTableData findMappingTableDataById(String networkTypeId, String networkClassId, String areaId) {
        Optional<MappingTableData> mappingTableData = mappingTableDataRepository.findById(new MappingTableDataId( Integer.parseInt(networkClassId), Integer.parseInt(areaId)));
        if(!mappingTableData.isPresent()) {
            throw new RecordNotFoundException(errorMessage.getRecordNotFound());
        }
        return mappingTableData.get();
    }

    public DataTable findAllPagingAndSorting(Specification<MappingTableData> specs, Pageable pageable){
        Page<MappingTableData> mappingTableData = mappingTableDataRepository.findAll(specs, pageable);
        return new DataTable(mappingTableData);
    }
}
