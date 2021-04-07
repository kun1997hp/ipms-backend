package com.viettel.demo.repository;

import com.viettel.demo.model.entity.Device;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Integer> {

    List<Device> findAllByDeviceNameEquals(String deviceName);

    Page<Device> findAll(Specification<Device>specs, Pageable pageable);

    Device getDeviceByDeviceId(int deviceId);
}
