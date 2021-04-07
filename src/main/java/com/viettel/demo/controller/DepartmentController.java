package com.viettel.demo.controller;

import com.viettel.demo.common.DataTable;
import com.viettel.demo.common.message.SuccessMessage;
import com.viettel.demo.common.response.DataTableResponse;
import com.viettel.demo.common.response.MessageResponse;
import com.viettel.demo.common.response.ObjectResponse;
import com.viettel.demo.model.entity.Department;
import com.viettel.demo.model.form.DepartmentForm;
import com.viettel.demo.service.DepartmentService;
import net.kaczmarzyk.spring.data.jpa.domain.*;
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
@RequestMapping("/departments")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private SuccessMessage successMessage;

    @GetMapping("/{departmentId}")
    public ResponseEntity<ObjectResponse> getDepartment(@PathVariable("departmentId") String departmentId) {
        Department department = departmentService.findDepartmentById(departmentId);
        ObjectResponse response = new ObjectResponse(successMessage.getView(), department);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ObjectResponse> postDepartment(@Valid @RequestBody DepartmentForm departmentForm) {
        Department department = departmentService.insertDepartment(departmentForm);
        ObjectResponse response = new ObjectResponse(successMessage.getAdd(), department);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{departmentId}")
    public ResponseEntity<MessageResponse> deleteDepartment(@PathVariable("departmentId") String departmentId) {
        departmentService.deleteDepartment(departmentId);
        return new ResponseEntity<>(new MessageResponse(successMessage.getDelete()), HttpStatus.OK);
    }

    @PutMapping("/{departmentId}")
    public ResponseEntity<MessageResponse> putDepartment(@PathVariable String departmentId, @Valid @RequestBody DepartmentForm departmentForm) {
        departmentService.updateDepartment(departmentId, departmentForm);
        return new ResponseEntity<>(new MessageResponse(successMessage.getEdit()), HttpStatus.OK);
    }

    @GetMapping("/v0")
    public ResponseEntity<DataTableResponse> getDepartments(
            @And({
                    @Spec(path = "departmentCode", params = "departmentCodeEqual", spec = Equal.class),
                    @Spec(path = "departmentName", params = "departmentNameLike", spec = Like.class),
                    @Spec(path = "parentId", params = "parentIdEqual", spec = Equal.class),
                    @Spec(path = "locationId", params = "locationIdEqual", spec = Equal.class),
                    @Spec(path = "departmentLevelId", params = "departmentLevelIdEqual", spec = Equal.class),
                    @Spec(path = "departmentTypeId", params = "departmentTypeIdEqual", spec = Equal.class),
                    @Spec(path = "status", params = "statusEqual", spec = Equal.class),
                    @Spec(path = "insertTime", params = "insertTimeIn", paramSeparator = ',', spec = In.class),
                    @Spec(path = "updateTime", params = "updateTimeIn", paramSeparator = ',', spec = In.class)
            }) Specification<Department> specs, Pageable pageable) {
        DataTable dataTable = departmentService.findAllPagingAndSorting(specs, pageable);
        DataTableResponse response = new DataTableResponse(successMessage.getView(), dataTable);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
