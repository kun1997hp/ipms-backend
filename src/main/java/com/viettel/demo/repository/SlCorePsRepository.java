package com.viettel.demo.repository;

import com.viettel.demo.model.entity.SlCorePs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlCorePsRepository extends JpaRepository<SlCorePs, Integer> {

    Page<SlCorePs> findAll(Specification<SlCorePs> specs, Pageable pageable);

    Page<SlCorePs> findAll(Pageable pageable);
}
