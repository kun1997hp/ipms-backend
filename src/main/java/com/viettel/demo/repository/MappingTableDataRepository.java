package com.viettel.demo.repository;

import com.viettel.demo.model.entity.MappingTableData;
import com.viettel.demo.model.entity.MappingTableDataId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MappingTableDataRepository extends JpaRepository<MappingTableData, MappingTableDataId> {

    Page<MappingTableData> findAll(Specification<MappingTableData> specs, Pageable pageable);
}
