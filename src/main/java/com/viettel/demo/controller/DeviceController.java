package com.viettel.demo.controller;

import com.querydsl.core.types.Predicate;
import com.viettel.demo.common.DataTable;
import com.viettel.demo.common.message.SuccessMessage;
import com.viettel.demo.common.response.DataTableResponse;
import com.viettel.demo.common.response.MessageResponse;
import com.viettel.demo.common.response.ObjectResponse;
import com.viettel.demo.model.entity.Device;
import com.viettel.demo.model.form.DeviceForm;
import com.viettel.demo.service.DeviceService;
import net.kaczmarzyk.spring.data.jpa.domain.*;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
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
import java.util.List;

@RestController
@RequestMapping("/devices")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;

    @Autowired
    private SuccessMessage successMessage;

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

}
