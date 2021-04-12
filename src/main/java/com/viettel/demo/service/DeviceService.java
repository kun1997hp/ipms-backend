package com.viettel.demo.service;

import com.viettel.demo.common.DataTable;
import com.viettel.demo.common.message.ErrorMessage;
import com.viettel.demo.exception.customexception.RecordNotFoundException;
import com.viettel.demo.model.entity.*;
import com.viettel.demo.model.form.DeviceForm;
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
public class DeviceService {
    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private ErrorMessage errorMessage;

    public Device insertDevice(DeviceForm deviceForm){
        Optional<Device> deviceFromId = deviceRepository.findById(deviceForm.getDeviceId());
        if(deviceFromId.isPresent()) {
            throw new DuplicateKeyException(errorMessage.getDuplicateId());
        }
        Device device = Device.builder().deviceId(deviceForm.getDeviceId())
                .deviceCode(deviceForm.getDeviceCode())
                .deviceName(deviceForm.getDeviceName())
                .deviceIp(deviceForm.getDeviceIp())
                .deviceIpFull(deviceForm.getDeviceIpFull())
                .description(deviceForm.getDescription())
                .deviceTypeId(deviceForm.getDeviceTypeId())
                .networkId(deviceForm.getNetworkId())
                .vendorId(deviceForm.getVendorId())
                .stationId(deviceForm.getStationId())
                .departmentByDepartmentId(departmentRepository.getDepartmentByDepartmentId(deviceForm.getDepartmentId()))
                .locationByLocationId(locationRepository.getLocationByLocationId(deviceForm.getLocationId()))
                .serial(deviceForm.getSerial())
                .status(deviceForm.getStatus())
                .insertTime(new Date())
                .updateTime(new Date())
                .checkPing(deviceForm.getCheckPing())
                .autoRescan(deviceForm.getAutoRescan())
                .sysDescription(deviceForm.getSysDescription())
                .sysVersion(deviceForm.getSysVersion())
                .sysSeries(deviceForm.getSysSeries())
                .snmpStatus(deviceForm.getSnmpStatus())
                .snmpCommunity(deviceForm.getSnmpCommunity())
                .snmpVersion(deviceForm.getSnmpVersion())
                .bits(deviceForm.getBits())
                .tableSyslog(deviceForm.getTableSyslog())
                .tableCounter(deviceForm.getTableCounter())
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
                .deviceCode(deviceForm.getDeviceCode())
                .deviceName(deviceForm.getDeviceName())
                .deviceIp(deviceForm.getDeviceIp())
                .deviceIpFull(deviceForm.getDeviceIpFull())
                .description(deviceForm.getDescription())
                .deviceTypeId(deviceForm.getDeviceTypeId())
                .networkId(deviceForm.getNetworkId())
                .vendorId(deviceForm.getVendorId())
                .stationId(deviceForm.getStationId())
                .departmentByDepartmentId(departmentRepository.getDepartmentByDepartmentId(deviceForm.getDepartmentId()))
                .locationByLocationId(locationRepository.getLocationByLocationId(deviceForm.getLocationId()))
                .serial(deviceForm.getSerial())
                .status(deviceForm.getStatus())
                .insertTime(deviceFromId.get().getInsertTime())
                .updateTime(new Date())
                .checkPing(deviceForm.getCheckPing())
                .autoRescan(deviceForm.getAutoRescan())
                .sysDescription(deviceForm.getSysDescription())
                .sysVersion(deviceForm.getSysVersion())
                .sysSeries(deviceForm.getSysSeries())
                .snmpStatus(deviceForm.getSnmpStatus())
                .snmpCommunity(deviceForm.getSnmpCommunity())
                .snmpVersion(deviceForm.getSnmpVersion())
                .bits(deviceForm.getBits())
                .tableSyslog(deviceForm.getTableSyslog())
                .tableCounter(deviceForm.getTableCounter())
                .build();
        deviceRepository.save(device);
    }

    public Device findDeviceById(String deviceId) {
        Device device = deviceRepository.getDeviceByDeviceId(Integer.parseInt(deviceId));
        if (device == null) {
            throw new RecordNotFoundException(errorMessage.getRecordNotFound());
        }
        return device;
    }
    public DataTable findAllPagingAndSorting(Specification<Device> specs, Pageable pageable){
        Page<Device> devices = deviceRepository.findAll(specs, pageable);
        return new DataTable(devices);
    }

}
