package com.viettel.demo.controller;

import com.viettel.demo.common.DataTable;
import com.viettel.demo.common.message.SuccessMessage;
import com.viettel.demo.common.response.DataTableResponse;
import com.viettel.demo.common.response.MessageResponse;
import com.viettel.demo.common.response.ObjectResponse;
import com.viettel.demo.model.entity.Device;
import com.viettel.demo.model.form.DeviceForm;
import com.viettel.demo.service.DeviceService;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.In;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
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
                    @Spec(path = "deviceCode", params = "deviceCodeLike", spec = Like.class),
                    @Spec(path = "deviceName", params = "deviceNameLike", spec = Like.class),
                    @Spec(path = "deviceIp", params = "deviceIpLike", spec = Like.class),
                    @Spec(path = "deviceIpFull", params = "deviceIpFullLike", spec = Like.class),
                    @Spec(path = "deviceTypeId", params = "deviceTypeIdEqual", spec = Equal.class),
                    @Spec(path = "networkId", params = "networkIdEqual", spec = Equal.class),
                    @Spec(path = "vendorId", params = "vendorIdEqual", spec = Equal.class),
                    @Spec(path = "stationId", params = "stationIdEqual", spec = Equal.class),
                    @Spec(path = "departmentId", params = "departmentIdEqual", spec = Equal.class),
                    @Spec(path = "locationId", params = "locationIdEqual", spec = Equal.class),
                    @Spec(path = "serial", params = "serialLike", spec = Like.class),
                    @Spec(path = "status", params = "statusEqual", spec = Equal.class),
                    @Spec(path = "insertTime", params = "insertTimeIn", paramSeparator = ',', spec = In.class),
                    @Spec(path = "updateTime", params = "updateTimeIn", paramSeparator = ',', spec = In.class),
                    @Spec(path = "checkPing", params = "checkPingEqual", spec = Equal.class),
                    @Spec(path = "autoRescan", params = "autoRescanEqual", spec = Equal.class),
                    @Spec(path = "sysDescription", params = "sysDescriptionLike", spec = Like.class),
                    @Spec(path = "sysVersion", params = "sysVersionLike", spec = Like.class),
                    @Spec(path = "sysSeries", params = "sysSeriesLike", spec = Like.class),
                    @Spec(path = "snmpStatus", params = "snmpStatusEqual", spec = Equal.class),
                    @Spec(path = "snmpCommunity", params = "snmpCommunityLike", spec = Like.class),
                    @Spec(path = "snmpVersion", params = "snmpVersionLike", spec = Like.class),
                    @Spec(path = "bits", params = "bitsEqual", spec = Equal.class),
                    @Spec(path = "tableSyslog", params = "tableSyslogLike", spec = Like.class),
                    @Spec(path = "tableCounter", params = "tableCounterLike", spec = Like.class)
            }) Specification<Device> specs, Pageable pageable) {
        DataTable dataTable = deviceService.findAllPagingAndSorting(specs, pageable);
        DataTableResponse response = new DataTableResponse(successMessage.getView(), dataTable);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
