package com.viettel.demo.repository;

import com.viettel.demo.model.entity.SlCoreIpbn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlCoreIpbnRepository extends JpaRepository<SlCoreIpbn, Integer> {

    Page<SlCoreIpbn> findAll(Specification<SlCoreIpbn> specs, Pageable pageable);

    Page<SlCoreIpbn> findAll(Pageable pageable);
}
