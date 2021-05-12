package com.viettel.demo.repository;

import com.viettel.demo.model.entity.SlAccessKv1;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlAccessKv1Repository extends JpaRepository<SlAccessKv1, Integer> {

    Page<SlAccessKv1> findAll(Specification<SlAccessKv1> specs, Pageable pageable);

    Page<SlAccessKv1> findAll(Pageable pageable);
}
