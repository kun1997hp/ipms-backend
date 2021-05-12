package com.viettel.demo.repository;

import com.viettel.demo.model.entity.SlCoreDcn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlCoreDcnRepository extends JpaRepository<SlCoreDcn, Integer> {

    Page<SlCoreDcn> findAll(Specification<SlCoreDcn> specs, Pageable pageable);

    Page<SlCoreDcn> findAll(Pageable pageable);
}
