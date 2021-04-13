package com.viettel.demo.repository;

import com.viettel.demo.model.entity.Station;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StationRepository extends JpaRepository<Station, Integer> {

    List<Station> findAllByStationNameEquals(String stationCode);

    Page<Station> findAll(Specification<Station>specs, Pageable pageable);

    Station getStationByStationId(int stationId);
}
