package com.viettel.demo.controller;

import com.viettel.demo.common.DataTable;
import com.viettel.demo.common.ExportUtils;
import com.viettel.demo.common.message.SuccessMessage;
import com.viettel.demo.common.response.DataTableResponse;
import com.viettel.demo.common.response.MessageResponse;
import com.viettel.demo.common.response.ObjectResponse;
import com.viettel.demo.model.entity.TableSyslog;
import com.viettel.demo.model.form.TableSyslogForm;
import com.viettel.demo.service.TableSyslogService;
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
@RequestMapping("/table-syslogs")
public class TableSyslogController {
    @Autowired
    private TableSyslogService tableSyslogService;

    @Autowired
    private SuccessMessage successMessage;

    @GetMapping("/{tableSyslog}")
    public ResponseEntity<ObjectResponse> getTableSyslog(@PathVariable("tableSyslog") String tableSyslogName) {
        TableSyslog tableSyslog = tableSyslogService.findTableSyslogById(tableSyslogName);
        ObjectResponse response = new ObjectResponse(successMessage.getView(), tableSyslog);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ObjectResponse> postTableSyslog(@Valid @RequestBody TableSyslogForm tableSyslogForm) {
        TableSyslog tableSyslog = tableSyslogService.insertTableSyslog(tableSyslogForm);
        ObjectResponse response = new ObjectResponse(successMessage.getAdd(), tableSyslog);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{tableSyslogId}")
    public ResponseEntity<MessageResponse> deleteTableSyslog(@PathVariable("tableSyslogId") String tableSyslogId) {
        tableSyslogService.deleteTableSyslog(tableSyslogId);
        return new ResponseEntity<>(new MessageResponse(successMessage.getDelete()), HttpStatus.OK);
    }

    @PutMapping("/{tableSyslogId}")
    public ResponseEntity<MessageResponse> putTableSyslog(@PathVariable String tableSyslogId, @Valid @RequestBody TableSyslogForm tableSyslogForm) {
        tableSyslogService.updateTableSyslog(tableSyslogId, tableSyslogForm);
        return new ResponseEntity<>(new MessageResponse(successMessage.getEdit()), HttpStatus.OK);
    }

    @GetMapping("/v0/export")
    public ResponseEntity<Resource> getBooksExport(Pageable pageable) throws Exception {
        DataTable dataTable = tableSyslogService.findAllUsingJPQLPagingAndSorting(pageable);
        int size=dataTable.getContent().size();
        System.out.println(size);
        File file = ExportUtils.writeExcel(TableSyslog.class, dataTable.getContent(), "TableSyslogs");
        return FileUtils.responseSourceFromFile(file);
    }

    @GetMapping("/v1")
    public ResponseEntity<DataTableResponse> getTableSyslogsV1(
            @Join(path = "faultLevel", alias = "faultLevel", distinct = false)
            @And({
                    @Spec(path = "tableSyslog", params = "tableSyslog", spec = LikeIgnoreCase.class),
                    @Spec(path = "whiteList", params = "whiteList", spec = LikeIgnoreCase.class),
                    @Spec(path = "blackList", params = "blackList", spec = LikeIgnoreCase.class),
                    @Spec(path = "faultLevel.faultLevelName", params = "faultLevelName", spec = LikeIgnoreCase.class),
                    @Spec(path = "insertTime", params = "insertTime", paramSeparator = ',', spec = In.class),
                    @Spec(path = "updateTime", params = "updateTime", paramSeparator = ',', spec = In.class),
                    @Spec(path = "status", params = "status", spec =  Equal.class),
            }) Specification<TableSyslog> specs, Pageable pageable) {
        DataTable dataTable = tableSyslogService.findAllPagingAndSorting(specs, pageable);

        DataTableResponse response = new DataTableResponse(successMessage.getView(), dataTable);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
