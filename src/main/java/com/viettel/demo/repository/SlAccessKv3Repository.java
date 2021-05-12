package com.viettel.demo.repository;

import com.viettel.demo.model.entity.SlAccessKv3;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlAccessKv3Repository extends JpaRepository<SlAccessKv3, Integer> {

    Page<SlAccessKv3> findAll(Specification<SlAccessKv3> specs, Pageable pageable);

    Page<SlAccessKv3> findAll(Pageable pageable);
}
