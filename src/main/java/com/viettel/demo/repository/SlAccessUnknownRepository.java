package com.viettel.demo.repository;

import com.viettel.demo.model.entity.SlAccessUnknown;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlAccessUnknownRepository extends JpaRepository<SlAccessUnknown, Integer> {

    Page<SlAccessUnknown> findAll(Specification<SlAccessUnknown> specs, Pageable pageable);

    Page<SlAccessUnknown> findAll(Pageable pageable);
}
