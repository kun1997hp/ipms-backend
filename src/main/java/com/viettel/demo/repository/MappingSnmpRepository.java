package com.viettel.demo.repository;

import com.viettel.demo.model.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MappingSnmpRepository extends JpaRepository<MappingSnmp, Integer> {

    Page<MappingSnmp> findAll(Specification<MappingSnmp> specs, Pageable pageable);

    MappingSnmp findByNetworkByNetworkTypeIdAndNetworkByNetworkClassIdAndVendorByVendorIdAndDeviceTypeByDeviceTypeIdAndLocationByAreaId(Network networkByNetworkTypeId, Network networkByNetworkClassId, Vendor vendorByVendorId, DeviceType deviceTypeByDeviceTypeId, Location locationByAreaId);

    MappingSnmp getMappingSnmpById(Integer id);
}
