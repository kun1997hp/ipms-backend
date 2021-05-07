package com.viettel.demo.controller;

import com.viettel.demo.common.DataTable;
import com.viettel.demo.common.ExportUtils;
import com.viettel.demo.common.message.SuccessMessage;
import com.viettel.demo.common.response.DataTableResponse;
import com.viettel.demo.common.response.MessageResponse;
import com.viettel.demo.common.response.ObjectResponse;
import com.viettel.demo.model.entity.Device;
import com.viettel.demo.model.entity.SyslogBase;
import com.viettel.demo.model.form.DeviceForm;
import com.viettel.demo.service.DeviceService;
import com.viettel.demo.service.SyslogBaseService;
import com.viettel.demo.util.FileUtils;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.In;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Join;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.File;

@RestController
@RequestMapping("/syslogs")
public class SyslogBaseController {
    @Autowired
    private SyslogBaseService syslogBaseService;

    @Autowired
    private SuccessMessage successMessage;

    @GetMapping("/v0/export")
    public ResponseEntity<Resource> getBooksExport(Pageable pageable) throws Exception {
        DataTable dataTable = syslogBaseService.findAllUsingJPQLPagingAndSorting(pageable);
        int size=dataTable.getContent().size();
        System.out.println(size);
        File file = ExportUtils.writeExcel(SyslogBase.class, dataTable.getContent(), "SyslogBases");
        return FileUtils.responseSourceFromFile(file);
    }

    @GetMapping("/v1")
    public ResponseEntity<DataTableResponse> getSyslogBaseV1(
            @And({
                    @Spec(path = "alarmId", params = "alarmId", spec = Equal.class),
                    @Spec(path = "deviceId", params = "deviceId", spec = Equal.class),
                    @Spec(path = "deviceTypeId", params = "deviceTypeId", spec = Equal.class),
                    @Spec(path = "vendorId", params = "vendorId", spec = Equal.class),
                    @Spec(path = "networkClassId", params = "networkClassId", spec = Equal.class),
                    @Spec(path = "networkTypeId", params = "networkTypeId", spec = Equal.class),
                    @Spec(path = "faultLevelId", params = "faultLevelId", spec = Equal.class),
                    @Spec(path = "areaId", params = "areaId", spec = Equal.class),
                    @Spec(path = "provinceId", params = "provinceId", spec = Equal.class),
                    @Spec(path = "locationId", params = "locationId", spec = Equal.class),
                    @Spec(path = "deviceIp", params = "deviceIp", spec = LikeIgnoreCase.class),
                    @Spec(path = "deviceName", params = "deviceName", spec = LikeIgnoreCase.class),
                    @Spec(path = "deviceTypeName", params = "deviceTypeName", spec = LikeIgnoreCase.class),
                    @Spec(path = "vendorCode", params = "vendorCode", spec = LikeIgnoreCase.class),
                    @Spec(path = "networkClassCode", params = "networkClassCode", spec = LikeIgnoreCase.class),
                    @Spec(path = "networkTypeCode", params = "networkTypeCode", spec = LikeIgnoreCase.class),
                    @Spec(path = "message", params = "message", spec = LikeIgnoreCase.class),
                    @Spec(path = "adapterName", params = "adapterName", spec = LikeIgnoreCase.class),
                    @Spec(path = "areaName", params = "areaName", spec = LikeIgnoreCase.class),
                    @Spec(path = "provinceName", params = "provinceName", spec = LikeIgnoreCase.class)
                    //date
                    //date
            }) Specification<SyslogBase> specs, Pageable pageable) {
        DataTable dataTable = syslogBaseService.findAllPagingAndSorting(specs, pageable);
        DataTableResponse response = new DataTableResponse(successMessage.getView(), dataTable);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
