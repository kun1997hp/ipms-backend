package com.viettel.demo.repository;

import com.viettel.demo.model.entity.Vendor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Integer> {

    List<Vendor> findAllByVendorNameEquals(String vendorCode);

    Page<Vendor> findAll(Specification<Vendor>specs, Pageable pageable);

    Vendor getVendorByVendorId(int vendorId);
}
