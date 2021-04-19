package com.viettel.demo.repository;

import com.viettel.demo.model.entity.Vendor;
import com.viettel.demo.model.view.VendorNameView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Integer> {

    List<VendorNameView> findBy();

    Vendor getVendorByVendorId(int vendorId);
}
