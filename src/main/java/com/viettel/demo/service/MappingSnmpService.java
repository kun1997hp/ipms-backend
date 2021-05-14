package com.viettel.demo.service;

import com.viettel.demo.common.DataTable;
import com.viettel.demo.common.message.ErrorMessage;
import com.viettel.demo.exception.customexception.InvalidInputException;
import com.viettel.demo.exception.customexception.RecordNotFoundException;
import com.viettel.demo.model.entity.*;
import com.viettel.demo.model.form.MappingSnmpForm;
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
public class MappingSnmpService {
    @Autowired
    private MappingSnmpRepository mappingSnmpRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private ErrorMessage errorMessage;

    @Autowired
    private NetworkRepository networkRepository;

    @Autowired
    private DeviceTypeRepository deviceTypeRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private VendorRepository vendorRepository;

    public boolean isValidNetworkId(MappingSnmpForm mappingSnmpForm) {
        if (networkRepository.getNetworkByNetworkId(mappingSnmpForm.getNetworkTypeId()).getParentId() != null)
            return false;
        else {
            return networkRepository.getNetworkByNetworkId(mappingSnmpForm.getNetworkClassId()).getParentId() == mappingSnmpForm.getNetworkTypeId();
        }
    }

    public MappingSnmp insertMappingSnmp(MappingSnmpForm mappingSnmpForm){
        Network networkType = networkRepository.getNetworkByNetworkId(mappingSnmpForm.getNetworkTypeId());
        Network networkClass = networkRepository.getNetworkByNetworkId(mappingSnmpForm.getNetworkClassId());
        Vendor vendor = vendorRepository.getVendorByVendorId(mappingSnmpForm.getVendorId());
        DeviceType deviceType = deviceTypeRepository.getDeviceTypeByDeviceTypeId(mappingSnmpForm.getDeviceTypeId());
        Location location = mappingSnmpForm.getAreaId() != null ? locationRepository.getLocationByLocationId(mappingSnmpForm.getAreaId()) : null;
        MappingSnmp mappingSnmpFromTuple = mappingSnmpRepository.findByNetworkByNetworkTypeIdAndNetworkByNetworkClassIdAndVendorByVendorIdAndDeviceTypeByDeviceTypeIdAndLocationByAreaId(networkType, networkClass, vendor, deviceType, location);
        if(mappingSnmpFromTuple != null) {
            throw new DuplicateKeyException(errorMessage.getDuplicateId());
        }
        if (!isValidNetworkId(mappingSnmpForm)) throw new InvalidInputException(errorMessage.getInvalidInput());
        MappingSnmp mappingSnmp = MappingSnmp.builder()
                .networkByNetworkTypeId(networkType)
                .networkByNetworkClassId(networkClass)
                .deviceTypeByDeviceTypeId(deviceType)
                .vendorByVendorId(vendor)
                .locationByAreaId(location)
                .snmpCommunity(mappingSnmpForm.getSnmpCommunity())
                .snmpVersion(mappingSnmpForm.getSnmpVersion())
                .status(mappingSnmpForm.getStatus())
                .insertTime(new Date())
                .updateTime(new Date())
                .build();
        return mappingSnmpRepository.save(mappingSnmp);
    }

    public void deleteMappingSnmp(String id){
        Optional<MappingSnmp> mappingSnmpFromId = mappingSnmpRepository.findById(Integer.parseInt(id));
        if(!mappingSnmpFromId.isPresent()) {
            throw new RecordNotFoundException(errorMessage.getRecordNotFound());
        }
        mappingSnmpRepository.delete(mappingSnmpFromId.get());
    }

    public void updateMappingSnmp(String id, MappingSnmpForm mappingSnmpForm){
        Optional<MappingSnmp> mappingSnmpFromId = mappingSnmpRepository.findById(Integer.parseInt(id));
        if(!mappingSnmpFromId.isPresent()) {
            throw new RecordNotFoundException(errorMessage.getRecordNotFound());
        }
        Network networkType = networkRepository.getNetworkByNetworkId(mappingSnmpForm.getNetworkTypeId());
        Network networkClass = networkRepository.getNetworkByNetworkId(mappingSnmpForm.getNetworkClassId());
        Vendor vendor = vendorRepository.getVendorByVendorId(mappingSnmpForm.getVendorId());
        DeviceType deviceType = deviceTypeRepository.getDeviceTypeByDeviceTypeId(mappingSnmpForm.getDeviceTypeId());
        Location location = mappingSnmpForm.getAreaId() != null ? locationRepository.getLocationByLocationId(mappingSnmpForm.getAreaId()) : null;
        MappingSnmp mappingSnmpFromTuple = mappingSnmpRepository.findByNetworkByNetworkTypeIdAndNetworkByNetworkClassIdAndVendorByVendorIdAndDeviceTypeByDeviceTypeIdAndLocationByAreaId(networkType, networkClass, vendor, deviceType, location);
        if(mappingSnmpFromTuple != null && mappingSnmpFromTuple != mappingSnmpFromId.get()) {
            throw new DuplicateKeyException(errorMessage.getDuplicateId());
        }
        if (!isValidNetworkId(mappingSnmpForm)) throw new InvalidInputException(errorMessage.getInvalidInput());
        MappingSnmp mappingSnmp = MappingSnmp.builder()
                .id(Integer.parseInt(id))
                .networkByNetworkTypeId(networkType)
                .networkByNetworkClassId(networkClass)
                .deviceTypeByDeviceTypeId(deviceType)
                .vendorByVendorId(vendor)
                .locationByAreaId(location)
                .snmpCommunity(mappingSnmpForm.getSnmpCommunity())
                .snmpVersion(mappingSnmpForm.getSnmpVersion())
                .status(mappingSnmpForm.getStatus())
                .insertTime(mappingSnmpFromId.get().getInsertTime())
                .updateTime(new Date())
                .build();
        mappingSnmpRepository.save(mappingSnmp);
    }

    public MappingSnmp findMappingSnmpByTuple(String networkTypeId, String networkClassId, String vendorId, String deviceTypeId, String areaId) {
        Network networkType = networkRepository.getNetworkByNetworkId(Integer.parseInt(networkTypeId));
        Network networkClass = networkRepository.getNetworkByNetworkId(Integer.parseInt(networkClassId));
        Vendor vendor = vendorRepository.getVendorByVendorId(Integer.parseInt(vendorId));
        DeviceType deviceType = deviceTypeRepository.getDeviceTypeByDeviceTypeId(Integer.parseInt(deviceTypeId));
        Location location = !areaId.equals("") ? locationRepository.getLocationByLocationId(Integer.parseInt(areaId)) : null;
        MappingSnmp mappingSnmpFromTuple = mappingSnmpRepository.findByNetworkByNetworkTypeIdAndNetworkByNetworkClassIdAndVendorByVendorIdAndDeviceTypeByDeviceTypeIdAndLocationByAreaId(networkType, networkClass, vendor, deviceType, location);
        if (mappingSnmpFromTuple == null) {
            throw new RecordNotFoundException(errorMessage.getRecordNotFound());
        }
        return mappingSnmpFromTuple;
    }

    public MappingSnmp findMappingSnmpById(String id) {
        MappingSnmp mappingSnmp = mappingSnmpRepository.getMappingSnmpById(Integer.parseInt(id));
        if (mappingSnmp == null) {
            throw new RecordNotFoundException(errorMessage.getRecordNotFound());
        }
        return mappingSnmp;
    }

    public DataTable findAllPagingAndSorting(Specification<MappingSnmp> specs, Pageable pageable){
        Page<MappingSnmp> mappingSnmp = mappingSnmpRepository.findAll(specs, pageable);
        return new DataTable(mappingSnmp);
    }
}
