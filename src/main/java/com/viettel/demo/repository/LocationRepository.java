package com.viettel.demo.repository;

import com.viettel.demo.model.entity.Location;
import com.viettel.demo.model.view.LocationNameView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {

    List<LocationNameView> findBy();

    Location getLocationByLocationId(int locationId);
}
