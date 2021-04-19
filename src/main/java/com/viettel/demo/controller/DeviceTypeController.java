package com.viettel.demo.controller;

import com.viettel.demo.common.message.SuccessMessage;
import com.viettel.demo.common.response.ObjectResponse;
import com.viettel.demo.model.entity.DeviceType;
import com.viettel.demo.model.view.DeviceTypeNameView;
import com.viettel.demo.service.DeviceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deviceTypes")
public class DeviceTypeController {
    @Autowired
    private DeviceTypeService deviceTypeService;

    @Autowired
    private SuccessMessage successMessage;

    @GetMapping("/{deviceTypeId}")
    public ResponseEntity<ObjectResponse> getDeviceType(@PathVariable("deviceTypeId") String deviceTypeId) {
        DeviceType deviceType = deviceTypeService.findDeviceTypeById(deviceTypeId);
        ObjectResponse response = new ObjectResponse(successMessage.getView(), deviceType);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/v0")
    public ResponseEntity<ObjectResponse> getDeviceTypes() {
        List<DeviceTypeNameView> deviceTypes = deviceTypeService.findBy();
        ObjectResponse response = new ObjectResponse(successMessage.getView(), deviceTypes);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
