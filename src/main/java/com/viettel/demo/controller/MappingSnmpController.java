package com.viettel.demo.controller;

import com.viettel.demo.common.DataTable;
import com.viettel.demo.common.message.SuccessMessage;
import com.viettel.demo.common.response.DataTableResponse;
import com.viettel.demo.common.response.MessageResponse;
import com.viettel.demo.common.response.ObjectResponse;
import com.viettel.demo.model.entity.MappingSnmp;
import com.viettel.demo.model.form.MappingSnmpForm;
import com.viettel.demo.service.MappingSnmpService;
import net.kaczmarzyk.spring.data.jpa.domain.*;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Join;
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
@RequestMapping("/mappingSnmps")
public class MappingSnmpController {
    @Autowired
    private MappingSnmpService mappingSnmpService;

    @Autowired
    private SuccessMessage successMessage;

    @GetMapping("/{id}")
    public ResponseEntity<ObjectResponse> getMappingSnmp(@PathVariable("id") String id) {
        MappingSnmp mappingSnmp = mappingSnmpService.findMappingSnmpById(id);
        ObjectResponse response = new ObjectResponse(successMessage.getView(), mappingSnmp);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ObjectResponse> postMappingSnmp(@Valid @RequestBody MappingSnmpForm mappingSnmpForm) {
        MappingSnmp mappingSnmp = mappingSnmpService.insertMappingSnmp(mappingSnmpForm);
        ObjectResponse response = new ObjectResponse(successMessage.getAdd(), mappingSnmp);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteMappingSnmp(@PathVariable("id") String id) {
        mappingSnmpService.deleteMappingSnmp(id);
        return new ResponseEntity<>(new MessageResponse(successMessage.getDelete()), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageResponse> putMappingSnmp(@PathVariable("id") String id, @Valid @RequestBody MappingSnmpForm mappingSnmpForm) {
        mappingSnmpService.updateMappingSnmp(id, mappingSnmpForm);
        return new ResponseEntity<>(new MessageResponse(successMessage.getEdit()), HttpStatus.OK);
    }

    @GetMapping("/v0")
    public ResponseEntity<DataTableResponse> getMappingSnmps(
            @Join(path = "networkByNetworkTypeId", alias = "networkType")
            @Join(path = "networkByNetworkClassId", alias = "networkClass")
            @Join(path = "deviceTypeByDeviceTypeId", alias = "deviceType")
            @Join(path = "vendorByVendorId", alias = "vendor")
            @Join(path = "locationByAreaId", alias = "location")
            @And({
                    @Spec(path = "module", params = "module", spec = LikeIgnoreCase.class),
                    @Spec(path = "tableSyslog", params = "tableSyslog", spec = LikeIgnoreCase.class),
                    @Spec(path = "tableCounter", params = "tableCounter", spec = LikeIgnoreCase.class),
                    @Spec(path = "tableCounterCustom", params = "tableCounterCustom", spec = LikeIgnoreCase.class),

                    @Spec(path = "networkType.networkName", params = "networkTypeName", spec = LikeIgnoreCase.class),
                    @Spec(path = "networkClass.networkName", params = "networkClassName", spec = LikeIgnoreCase.class),
                    @Spec(path = "vendor.vendorName", params = "vendorName", spec = LikeIgnoreCase.class),
                    @Spec(path = "deviceType.deviceTypeName", params = "deviceTypeName", spec = LikeIgnoreCase.class),
                    @Spec(path = "location.locationName", params="areaName", spec = LikeIgnoreCase.class),

                    @Spec(path = "status", params = "status", spec = Equal.class),
                    @Spec(path = "insertTime", params = "insertTime", paramSeparator = ',', spec = Between.class, config = "yyyy-MM-dd'T'HH:mm:ss"),
                    @Spec(path = "updateTime", params = "updateTime", paramSeparator = ',', spec = Between.class, config = "yyyy-MM-dd'T'HH:mm:ss"),
            }) Specification<MappingSnmp> specs, Pageable pageable) {
        DataTable dataTable = mappingSnmpService.findAllPagingAndSorting(specs, pageable);
        DataTableResponse response = new DataTableResponse(successMessage.getView(), dataTable);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
