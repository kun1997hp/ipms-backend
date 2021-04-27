package com.viettel.demo.service;

import com.viettel.demo.common.message.ErrorMessage;
import com.viettel.demo.exception.customexception.RecordNotFoundException;
import com.viettel.demo.model.entity.*;
import com.viettel.demo.model.view.StationCodeView;
import com.viettel.demo.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.util.List;

@Service
public class StationService {
    @Autowired
    private StationRepository stationRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private ErrorMessage errorMessage;

    public Station findStationById(String stationId) {
        Station station = stationRepository.getStationByStationId(Integer.parseInt(stationId));
        if (station == null) {
            throw new RecordNotFoundException(errorMessage.getRecordNotFound());
        }
        return station;
    }

    public List<StationCodeView> findBy(){
        return stationRepository.findBy();
    }

}
