package com.viettel.demo.repository;

import com.viettel.demo.model.entity.SlCoreIpth;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlCoreIpthRepository extends JpaRepository<SlCoreIpth, Integer> {

    Page<SlCoreIpth> findAll(Specification<SlCoreIpth> specs, Pageable pageable);

    Page<SlCoreIpth> findAll(Pageable pageable);
}
