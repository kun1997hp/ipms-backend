package com.viettel.demo.repository;

import com.viettel.demo.model.entity.SlCoreMetroCtcv;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlCoreMetroCtcvRepository extends JpaRepository<SlCoreMetroCtcv, Integer> {

    Page<SlCoreMetroCtcv> findAll(Specification<SlCoreMetroCtcv> specs, Pageable pageable);

    Page<SlCoreMetroCtcv> findAll(Pageable pageable);
}
