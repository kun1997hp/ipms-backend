package com.viettel.demo.repository;

import com.viettel.demo.model.entity.DeviceType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceTypeRepository extends JpaRepository<DeviceType, Integer> {

    List<DeviceType> findAllByDeviceTypeNameEquals(String deviceTypeCode);

    Page<DeviceType> findAll(Specification<DeviceType>specs, Pageable pageable);

    DeviceType getDeviceTypeByDeviceTypeId(int deviceTypeId);
}
