package com.viettel.demo.repository;

import com.viettel.demo.model.entity.SlCoreMetroAgg;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlCoreMetroAggRepository extends JpaRepository<SlCoreMetroAgg, Integer> {

    Page<SlCoreMetroAgg> findAll(Specification<SlCoreMetroAgg> specs, Pageable pageable);

    Page<SlCoreMetroAgg> findAll(Pageable pageable);
}
