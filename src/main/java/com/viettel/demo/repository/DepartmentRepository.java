package com.viettel.demo.repository;

import com.viettel.demo.model.entity.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    List<Department> findAllByDepartmentNameEquals(String departmentCode);

    Page<Department> findAll(Specification<Department>specs, Pageable pageable);

    Department getDepartmentByDepartmentId(int departmentId);
}
