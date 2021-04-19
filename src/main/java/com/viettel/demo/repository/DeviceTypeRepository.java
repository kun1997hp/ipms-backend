package com.viettel.demo.repository;

import com.viettel.demo.model.entity.DeviceType;
import com.viettel.demo.model.view.DeviceTypeNameView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceTypeRepository extends JpaRepository<DeviceType, Integer> {

    List<DeviceTypeNameView> findBy();

    DeviceType getDeviceTypeByDeviceTypeId(int deviceTypeId);
}
