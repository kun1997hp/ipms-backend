package com.viettel.demo.repository;

import com.viettel.demo.model.entity.SlAccessKv2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlAccessKv2Repository extends JpaRepository<SlAccessKv2, Integer> {

    Page<SlAccessKv2> findAll(Specification<SlAccessKv2> specs, Pageable pageable);

    Page<SlAccessKv2> findAll(Pageable pageable);
}
