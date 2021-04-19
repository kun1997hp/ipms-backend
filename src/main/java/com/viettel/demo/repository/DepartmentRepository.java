package com.viettel.demo.repository;

import com.viettel.demo.model.entity.Department;
import com.viettel.demo.model.view.DepartmentNameView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    List<DepartmentNameView> findBy();

    Department getDepartmentByDepartmentId(int departmentId);
}
