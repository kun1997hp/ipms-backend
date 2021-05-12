package com.viettel.demo.repository;

import com.viettel.demo.model.entity.SlAccessMetroKv2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlAccessMetroKv2Repository extends JpaRepository<SlAccessMetroKv2, Integer> {

    Page<SlAccessMetroKv2> findAll(Specification<SlAccessMetroKv2> specs, Pageable pageable);

    Page<SlAccessMetroKv2> findAll(Pageable pageable);
}
