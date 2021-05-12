package com.viettel.demo.repository;

import com.viettel.demo.model.entity.SlCoreUnknown;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlCoreUnknownRepository extends JpaRepository<SlCoreUnknown, Integer> {

    Page<SlCoreUnknown> findAll(Specification<SlCoreUnknown> specs, Pageable pageable);

    Page<SlCoreUnknown> findAll(Pageable pageable);
}
