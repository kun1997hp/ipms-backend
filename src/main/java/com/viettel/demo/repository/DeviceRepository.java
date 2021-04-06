package com.viettel.demo.repository;

import com.querydsl.core.types.dsl.StringPath;
import com.viettel.demo.model.entity.Device;
import com.viettel.demo.model.entity.QDevice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Integer>,
        QuerydslPredicateExecutor<Device>, QuerydslBinderCustomizer<QDevice> {

    List<Device> findAllByDeviceNameEquals(String deviceName);

    @Query("select b from Device b")
    Page<Device> findAllUsingJPQLPagingAndSorting(Specification<Device>specs, Pageable pageable);

    Device getDeviceByDeviceId(int deviceId);

    @Override
    default void customize(QuerydslBindings bindings, QDevice root) {
        bindings.bind(String.class).first(
                (StringPath path, String value) -> path.containsIgnoreCase(value));
        bindings.excluding(root.deviceName);
    }
}
