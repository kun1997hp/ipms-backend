package com.viettel.demo.repository;

import com.viettel.demo.model.entity.SlAccessMetroKv1;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlAccessMetroKv1Repository extends JpaRepository<SlAccessMetroKv1, Integer> {

    Page<SlAccessMetroKv1> findAll(Specification<SlAccessMetroKv1> specs, Pageable pageable);

    Page<SlAccessMetroKv1> findAll(Pageable pageable);
}
