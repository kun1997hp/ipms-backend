package com.viettel.demo.repository;

import com.viettel.demo.model.entity.Station;
import com.viettel.demo.model.view.StationCodeView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StationRepository extends JpaRepository<Station, Integer> {

    List<StationCodeView> findBy();

    Station getStationByStationId(int stationId);
}
