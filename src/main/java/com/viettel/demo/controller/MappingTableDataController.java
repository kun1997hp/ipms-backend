package com.viettel.demo.controller;

import com.viettel.demo.common.DataTable;
import com.viettel.demo.common.message.SuccessMessage;
import com.viettel.demo.common.response.DataTableResponse;
import com.viettel.demo.common.response.MessageResponse;
import com.viettel.demo.common.response.ObjectResponse;
import com.viettel.demo.model.entity.MappingTableData;
import com.viettel.demo.model.form.MappingTableDataForm;
import com.viettel.demo.service.MappingTableDataService;
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
@RequestMapping("/mappingTableDatas")
public class MappingTableDataController {
    @Autowired
    private MappingTableDataService mappingTableDataService;

    @Autowired
    private SuccessMessage successMessage;

    @GetMapping("/{networkTypeId}_{networkClassId}_{areaId}")
    public ResponseEntity<ObjectResponse> getDevice(@PathVariable("networkTypeId") String networkTypeId, @PathVariable("networkClassId") String networkClassId, @PathVariable("areaId") String areaId) {
        MappingTableData mappingTableData = mappingTableDataService.findMappingTableDataById(networkTypeId, networkClassId, areaId);
        ObjectResponse response = new ObjectResponse(successMessage.getView(), mappingTableData);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ObjectResponse> postMappingTableData(@Valid @RequestBody MappingTableDataForm mappingTableDataForm) {
        MappingTableData mappingTableData = mappingTableDataService.insertMappingTableData(mappingTableDataForm);
        ObjectResponse response = new ObjectResponse(successMessage.getAdd(), mappingTableData);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{networkTypeId}_{networkClassId}_{areaId}")
    public ResponseEntity<MessageResponse> deleteMappingTableData(@PathVariable("networkTypeId") String networkTypeId, @PathVariable("networkClassId") String networkClassId, @PathVariable("areaId") String areaId) {
        mappingTableDataService.deleteMappingTableData(networkTypeId, networkClassId, areaId);
        return new ResponseEntity<>(new MessageResponse(successMessage.getDelete()), HttpStatus.OK);
    }

    @PutMapping("/{networkTypeId}_{networkClassId}_{areaId}")
    public ResponseEntity<MessageResponse> putMappingTableData(@PathVariable("networkTypeId") String networkTypeId, @PathVariable("networkClassId") String networkClassId, @PathVariable("areaId") String areaId, @Valid @RequestBody MappingTableDataForm mappingTableDataForm) {
        mappingTableDataService.updateMappingTableData(networkTypeId, networkClassId, areaId, mappingTableDataForm);
        return new ResponseEntity<>(new MessageResponse(successMessage.getEdit()), HttpStatus.OK);
    }

    @GetMapping("/v0")
    public ResponseEntity<DataTableResponse> getMappingTableDatas(
            @Join(path = "networkByNetworkTypeId", alias = "networkType")
            @Join(path = "networkByNetworkClassId", alias = "networkClass")
            @Join(path = "locationByAreaId", alias = "location")
            @And({
                    @Spec(path = "module", params = "module", spec = LikeIgnoreCase.class),
                    @Spec(path = "tableSyslog", params = "tableSyslog", spec = LikeIgnoreCase.class),
                    @Spec(path = "tableCounter", params = "tableCounter", spec = LikeIgnoreCase.class),
                    @Spec(path = "tableCounterCustom", params = "tableCounterCustom", spec = LikeIgnoreCase.class),

                    @Spec(path = "networkType.networkName", params = "networkType", spec = LikeIgnoreCase.class),
                    @Spec(path = "networkClass.networkName", params = "networkClass", spec = LikeIgnoreCase.class),
                    @Spec(path = "location.locationName", params="area", spec = LikeIgnoreCase.class),

                    @Spec(path = "status", params = "status", spec = Equal.class),
                    @Spec(path = "insertTime", params = "insertTime", paramSeparator = ',', spec = Between.class, config = "yyyy-MM-dd'T'HH:mm:ss"),
                    @Spec(path = "updateTime", params = "updateTime", paramSeparator = ',', spec = Between.class, config = "yyyy-MM-dd'T'HH:mm:ss"),
            }) Specification<MappingTableData> specs, Pageable pageable) {
        DataTable dataTable = mappingTableDataService.findAllPagingAndSorting(specs, pageable);
        DataTableResponse response = new DataTableResponse(successMessage.getView(), dataTable);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
