package com.viettel.demo.repository;

import com.viettel.demo.model.entity.SlCoreMpbn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlCoreMpbnRepository extends JpaRepository<SlCoreMpbn, Integer> {

    Page<SlCoreMpbn> findAll(Specification<SlCoreMpbn> specs, Pageable pageable);

    Page<SlCoreMpbn> findAll(Pageable pageable);
}
