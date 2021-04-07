package com.viettel.demo.repository;

import com.viettel.demo.model.entity.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {

    List<Location> findAllByLocationNameEquals(String locationCode);

    Page<Location> findAll(Specification<Location>specs, Pageable pageable);

    Location getLocationByLocationId(int locationId);
}
