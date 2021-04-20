package com.viettel.demo.controller;

import com.viettel.demo.common.DataTable;
import com.viettel.demo.common.ExportUtils;
import com.viettel.demo.common.message.SuccessMessage;
import com.viettel.demo.common.response.DataTableResponse;
import com.viettel.demo.common.response.MessageResponse;
import com.viettel.demo.common.response.ObjectResponse;
import com.viettel.demo.model.entity.Device;
import com.viettel.demo.model.form.DeviceForm;
import com.viettel.demo.service.DeviceService;
import com.viettel.demo.util.FileUtils;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.In;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Join;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.validation.Valid;
import java.io.File;

@RestController
@RequestMapping("/devices")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;

    @Autowired
    private SuccessMessage successMessage;

    @GetMapping("/{deviceId}")
    public ResponseEntity<ObjectResponse> getDevice(@PathVariable("deviceId") String deviceId) {
        Device device = deviceService.findDeviceById(deviceId);
        ObjectResponse response = new ObjectResponse(successMessage.getView(), device);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ObjectResponse> postDevice(@Valid @RequestBody DeviceForm deviceForm) {
        Device device = deviceService.insertDevice(deviceForm);
        ObjectResponse response = new ObjectResponse(successMessage.getAdd(), device);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{deviceId}")
    public ResponseEntity<MessageResponse> deleteDevice(@PathVariable("deviceId") String deviceId) {
        deviceService.deleteDevice(deviceId);
        return new ResponseEntity<>(new MessageResponse(successMessage.getDelete()), HttpStatus.OK);
    }

    @PutMapping("/{deviceId}")
    public ResponseEntity<MessageResponse> putDevice(@PathVariable String deviceId, @Valid @RequestBody DeviceForm deviceForm) {
        deviceService.updateDevice(deviceId, deviceForm);
        return new ResponseEntity<>(new MessageResponse(successMessage.getEdit()), HttpStatus.OK);
    }

    @GetMapping("/v0")
    public ResponseEntity<DataTableResponse> getDevices(
            @And({
                    @Spec(path = "deviceCode", params = "deviceCode", spec = Like.class),
                    @Spec(path = "deviceName", params = "deviceName", spec = Like.class),
                    @Spec(path = "deviceIp", params = "deviceIp", spec = Like.class),
                    @Spec(path = "deviceIpFull", params = "deviceIpFull", spec = Like.class),
                    @Spec(path = "deviceTypeId", params = "deviceTypeId", spec = Equal.class),
                    @Spec(path = "networkId", params = "networkId", spec = Equal.class),
                    @Spec(path = "vendorId", params = "vendorId", spec = Equal.class),
                    @Spec(path = "stationId", params = "stationId", spec = Equal.class),
                    @Spec(path = "departmentId", params = "departmentId", spec = Equal.class),
                    @Spec(path = "locationId", params = "locationId", spec = Equal.class),
                    @Spec(path = "serial", params = "serial", spec = Like.class),
                    @Spec(path = "status", params = "status", spec = Equal.class),
                    @Spec(path = "insertTime", params = "insertTime", paramSeparator = ',', spec = In.class),
                    @Spec(path = "updateTime", params = "updateTime", paramSeparator = ',', spec = In.class),
                    @Spec(path = "checkPing", params = "checkPing", spec = Equal.class),
                    @Spec(path = "autoRescan", params = "autoRescan", spec = Equal.class),
                    @Spec(path = "sysDescription", params = "sysDescription", spec = Like.class),
                    @Spec(path = "sysVersion", params = "sysVersion", spec = Like.class),
                    @Spec(path = "sysSeries", params = "sysSeries", spec = Like.class),
                    @Spec(path = "snmpStatus", params = "snmpStatus", spec = Equal.class),
                    @Spec(path = "snmpCommunity", params = "snmpCommunity", spec = Like.class),
                    @Spec(path = "snmpVersion", params = "snmpVersion", spec = Like.class),
                    @Spec(path = "bits", params = "bits", spec = Equal.class),
                    @Spec(path = "tableSyslog", params = "tableSyslog", spec = Like.class),
                    @Spec(path = "tableCounter", params = "tableCounter", spec = Like.class)
            }) Specification<Device> specs, Pageable pageable) {
        DataTable dataTable = deviceService.findAllPagingAndSorting(specs, pageable);
        DataTableResponse response = new DataTableResponse(successMessage.getView(), dataTable);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/v0/export")
    public ResponseEntity<Resource> getBooksExport(@And({
            @Spec(path = "deviceCode", params = "deviceCode", spec = Like.class),
            @Spec(path = "deviceName", params = "deviceName", spec = Like.class),
            @Spec(path = "deviceIp", params = "deviceIp", spec = Like.class),
            @Spec(path = "deviceIpFull", params = "deviceIpFull", spec = Like.class),
            @Spec(path = "deviceTypeId", params = "deviceTypeId", spec = Equal.class),
            @Spec(path = "networkId", params = "networkId", spec = Equal.class),
            @Spec(path = "vendorId", params = "vendorId", spec = Equal.class),
            @Spec(path = "stationId", params = "stationId", spec = Equal.class),
            @Spec(path = "departmentId", params = "departmentId", spec = Equal.class),
            @Spec(path = "locationId", params = "locationId", spec = Equal.class),
            @Spec(path = "serial", params = "serial", spec = Like.class),
            @Spec(path = "status", params = "status", spec = Equal.class),
            @Spec(path = "insertTime", params = "insertTime", paramSeparator = ',', spec = In.class),
            @Spec(path = "updateTime", params = "updateTime", paramSeparator = ',', spec = In.class),
            @Spec(path = "checkPing", params = "checkPing", spec = Equal.class),
            @Spec(path = "autoRescan", params = "autoRescan", spec = Equal.class),
            @Spec(path = "sysDescription", params = "sysDescription", spec = Like.class),
            @Spec(path = "sysVersion", params = "sysVersion", spec = Like.class),
            @Spec(path = "sysSeries", params = "sysSeries", spec = Like.class),
            @Spec(path = "snmpStatus", params = "snmpStatus", spec = Equal.class),
            @Spec(path = "snmpCommunity", params = "snmpCommunity", spec = Like.class),
            @Spec(path = "snmpVersion", params = "snmpVersion", spec = Like.class),
            @Spec(path = "bits", params = "bits", spec = Equal.class),
            @Spec(path = "tableSyslog", params = "tableSyslog", spec = Like.class),
            @Spec(path = "tableCounter", params = "tableCounter", spec = Like.class)
    }) Specification<Device> specs, Pageable pageable) throws Exception {
        DataTable dataTable = deviceService.findAllPagingAndSorting(specs, pageable);
        File file = ExportUtils.writeExcel(Device.class, dataTable.getContent(), "Devices");
        return FileUtils.responseSourceFromFile(file);
    }

    @GetMapping("/v1")
    public ResponseEntity<DataTableResponse> getDevicesV1(
            @Join(path = "locationByLocationId", alias = "location")
            @Join(path = "departmentByDepartmentId", alias = "department")
            @Join(path = "stationByStationId", alias = "station")
            @Join(path = "vendorByVendorId", alias = "vendor")
            @Join(path = "networkByNetworkId", alias = "network")
            @Join(path = "deviceTypeByDeviceTypeId", alias = "deviceType")
            @And({
                    @Spec(path = "deviceCode", params = "deviceCode", spec = LikeIgnoreCase.class),
                    @Spec(path = "deviceName", params = "deviceName", spec = LikeIgnoreCase.class),
                    @Spec(path = "deviceIp", params = "deviceIp", spec = Like.class),
                    @Spec(path = "deviceIpFull", params = "deviceIpFull", spec = Like.class),
                    @Spec(path = "description", params = "description", spec = LikeIgnoreCase.class),

                    @Spec(path = "deviceType.deviceTypeName", params = "deviceTypeName", spec = LikeIgnoreCase.class),
                    @Spec(path = "network.networkName", params = "networkName", spec = LikeIgnoreCase.class),
                    @Spec(path = "vendor.vendorName", params = "vendorName", spec = LikeIgnoreCase.class),
                    @Spec(path = "station.stationName", params = "stationName", spec = LikeIgnoreCase.class),
                    @Spec(path = "department.departmentName", params = "departmentName", spec = LikeIgnoreCase.class),
                    @Spec(path = "location.locationName", params="locationName", spec = LikeIgnoreCase.class),

                    @Spec(path = "serial", params = "serial", spec = LikeIgnoreCase.class),
                    @Spec(path = "status", params = "status", spec = Equal.class),
                    @Spec(path = "insertTime", params = "insertTime", paramSeparator = ',', spec = In.class),
                    @Spec(path = "updateTime", params = "updateTime", paramSeparator = ',', spec = In.class),
                    @Spec(path = "checkPing", params = "checkPing", spec = Equal.class),
                    @Spec(path = "autoRescan", params = "autoRescan", spec = Equal.class),
                    @Spec(path = "sysDescription", params = "sysDescription", spec = LikeIgnoreCase.class),
                    @Spec(path = "sysVersion", params = "sysVersion", spec = LikeIgnoreCase.class),
                    @Spec(path = "sysSeries", params = "sysSeries", spec = LikeIgnoreCase.class),
                    @Spec(path = "snmpStatus", params = "snmpStatus", spec = Equal.class),
                    @Spec(path = "snmpCommunity", params = "snmpCommunity", spec = LikeIgnoreCase.class),
                    @Spec(path = "snmpVersion", params = "snmpVersion", spec = LikeIgnoreCase.class),
                    @Spec(path = "bits", params = "bits", spec = Equal.class),
                    @Spec(path = "tableSyslog", params = "tableSyslog", spec = LikeIgnoreCase.class),
                    @Spec(path = "tableCounter", params = "tableCounter", spec = LikeIgnoreCase.class)
            }) Specification<Device> specs, Pageable pageable) {
        DataTable dataTable = deviceService.findAllPagingAndSorting(specs, pageable);
        DataTableResponse response = new DataTableResponse(successMessage.getView(), dataTable);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
