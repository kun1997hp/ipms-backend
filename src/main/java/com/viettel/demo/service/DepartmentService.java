package com.viettel.demo.service;

import com.viettel.demo.common.DataTable;
import com.viettel.demo.common.message.ErrorMessage;
import com.viettel.demo.exception.customexception.RecordNotFoundException;
import com.viettel.demo.model.entity.*;
import com.viettel.demo.model.form.DepartmentForm;
import com.viettel.demo.repository.DepartmentRepository;
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
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private ErrorMessage errorMessage;

    public Department insertDepartment(DepartmentForm departmentForm){
        Optional<Department> departmentFromId = departmentRepository.findById(departmentForm.getDepartmentId());
        if(departmentFromId.isPresent()) {
            throw new DuplicateKeyException(errorMessage.getDuplicateId());
        }
        Department department = Department.builder().departmentId(departmentForm.getDepartmentId())
                .departmentCode(departmentForm.getDepartmentCode())
                .departmentName(departmentForm.getDepartmentName())
                .description(departmentForm.getDescription())
                .parentId(departmentForm.getParentId())
                .locationId(departmentForm.getLocationId())
                .departmentLevelId(departmentForm.getDepartmentLevelId())
                .departmentTypeId(departmentForm.getDepartmentTypeId())
                .status(departmentForm.getStatus())
                .insertTime(new Date())
                .updateTime(new Date())
                .build();
        return departmentRepository.save(department);
    }

    public void deleteDepartment(String departmentId){
        Optional<Department> departmentFromId = departmentRepository.findById(Integer.parseInt(departmentId));
        if(!departmentFromId.isPresent()) {
            throw new RecordNotFoundException(errorMessage.getRecordNotFound());
        }
        departmentRepository.delete(departmentFromId.get());
    }

    public void updateDepartment(String departmentId, DepartmentForm departmentForm){
        Optional<Department> departmentFromId = departmentRepository.findById(Integer.parseInt(departmentId));
        if(!departmentFromId.isPresent()) {
            throw new RecordNotFoundException(errorMessage.getRecordNotFound());
        }
        Department department = Department.builder().departmentId(Integer.parseInt(departmentId))
                .departmentCode(departmentForm.getDepartmentCode())
                .departmentName(departmentForm.getDepartmentName())
                .description(departmentForm.getDescription())
                .parentId(departmentForm.getParentId())
                .locationId(departmentForm.getLocationId())
                .departmentLevelId(departmentForm.getDepartmentLevelId())
                .departmentTypeId(departmentForm.getDepartmentTypeId())
                .status(departmentForm.getStatus())
                .insertTime(departmentFromId.get().getInsertTime())
                .updateTime(new Date())
                .build();
        departmentRepository.save(department);
    }

    public Department findDepartmentById(String departmentId) {
        Department department = departmentRepository.getDepartmentByDepartmentId(Integer.parseInt(departmentId));
        if (department == null) {
            throw new RecordNotFoundException(errorMessage.getRecordNotFound());
        }
        return department;
    }

    public DataTable findAllPagingAndSorting(Specification<Department> specs, Pageable pageable){
        Page<Department> departments = departmentRepository.findAll(specs, pageable);
        return new DataTable(departments);
    }

}
