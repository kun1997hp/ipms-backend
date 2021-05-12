package com.viettel.demo.repository;

import com.viettel.demo.model.entity.SlAccessMetroKv3;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlAccessMetroKv3Repository extends JpaRepository<SlAccessMetroKv3, Integer> {

    Page<SlAccessMetroKv3> findAll(Specification<SlAccessMetroKv3> specs, Pageable pageable);

    Page<SlAccessMetroKv3> findAll(Pageable pageable);
}
