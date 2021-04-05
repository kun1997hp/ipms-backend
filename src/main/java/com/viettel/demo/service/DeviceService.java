package com.viettel.demo.service;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.viettel.demo.common.DataTable;
import com.viettel.demo.common.message.ErrorMessage;
import com.viettel.demo.exception.customexception.BusinessException;
import com.viettel.demo.exception.customexception.RecordNotFoundException;
import com.viettel.demo.model.entity.*;
import com.viettel.demo.model.form.DeviceForm;
import com.viettel.demo.repository.DeviceRepository;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Service
public class DeviceService {
    @Autowired
    private DeviceRepository deviceRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private ErrorMessage errorMessage;

    public Device insertDevice(DeviceForm deviceForm){
        List<Device> devices = deviceRepository.findAllByDeviceNameEquals(deviceForm.getDeviceName());
        if(devices.size() > 0){
            throw new BusinessException(errorMessage.getIsbnNotDuplicate());
        }
        Device device = Device.builder().deviceName(deviceForm.getDeviceName())
                .serial(deviceForm.getSerial())
                .deviceTypeId(deviceForm.getDeviceTypeId())
                .vendorId(deviceForm.getVendorId())
                .networkId(deviceForm.getNetworkId())
                .stationId(deviceForm.getStationId())
                .departmentId(deviceForm.getDepartmentId())
                .locationId(deviceForm.getLocationId())
                .status(deviceForm.getStatus())
                .insertTime(deviceForm.getInsertTime())
                .updateTime(deviceForm.getUpdateTime())
                .build();
        return deviceRepository.save(device);
    }

    public void deleteDevice(String deviceId){
        Optional<Device> deviceFromId = deviceRepository.findById(Integer.parseInt(deviceId));
        if(!deviceFromId.isPresent()) {
            throw new RecordNotFoundException(errorMessage.getRecordNotFound());
        }
        deviceRepository.delete(deviceFromId.get());
    }

    public void updateDevice(String deviceId, DeviceForm deviceForm){
        Optional<Device> deviceFromId = deviceRepository.findById(Integer.parseInt(deviceId));
        if(!deviceFromId.isPresent()) {
            throw new RecordNotFoundException(errorMessage.getRecordNotFound());
        }
        Device device = Device.builder().deviceId(Integer.parseInt(deviceId))
                .deviceName(deviceForm.getDeviceName())
                .serial(deviceForm.getSerial())
                .deviceTypeId(deviceForm.getDeviceTypeId())
                .vendorId(deviceForm.getVendorId())
                .networkId(deviceForm.getNetworkId())
                .stationId(deviceForm.getStationId())
                .departmentId(deviceForm.getDepartmentId())
                .locationId(deviceForm.getLocationId())
                .status(deviceForm.getStatus())
                .insertTime(deviceForm.getInsertTime())
                .updateTime(deviceForm.getUpdateTime())
                .build();
        deviceRepository.save(device);
    }

    public Page<Device> findAllUsingFunctionName(Pageable pageable){
        return deviceRepository.findAll(pageable);
    }

    public Page<Device> findAllUsingJPQL(Specification<Device> specs, Pageable pageable){
        return deviceRepository.findAllUsingJPQL(Specification.where(specs), pageable);
    }

    public Device findDeviceById(String deviceId) {
        Device device = deviceRepository.getDeviceByDeviceId(Integer.parseInt(deviceId));
        if (device == null) {
            throw new RecordNotFoundException(errorMessage.getRecordNotFound());
        }
        return device;
    }
    public DataTable findAllUsingJPQLPagingAndSorting(Specification<Device> specs, Pageable pageable){
        Page<Device> devices = deviceRepository.findAll(specs, pageable);
        return new DataTable(devices);
    }

    // Select some field of table, other field is null
    public List<?> queryFromQuerydsl(){
        return null;
    }
}
