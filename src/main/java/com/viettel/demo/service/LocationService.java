package com.viettel.demo.service;

import com.viettel.demo.common.DataTable;
import com.viettel.demo.common.message.ErrorMessage;
import com.viettel.demo.exception.customexception.RecordNotFoundException;
import com.viettel.demo.model.entity.*;
import com.viettel.demo.model.form.LocationForm;
import com.viettel.demo.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.sql.Date;
import java.util.Optional;

@Service
public class LocationService {
    @Autowired
    private LocationRepository locationRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private ErrorMessage errorMessage;

    public Location insertLocation(LocationForm locationForm){
        Optional<Location> locationFromId = locationRepository.findById(locationForm.getLocationId());
        if(locationFromId.isPresent()) {
            throw new DuplicateKeyException(errorMessage.getDuplicateId());
        }
        Location location = Location.builder().locationId(locationForm.getLocationId())
                .locationCode(locationForm.getLocationCode())
                .locationName(locationForm.getLocationName())
                .description(locationForm.getDescription())
                .parentId(locationForm.getParentId())
                .locationLevelId(locationForm.getLocationLevelId())
                .status(locationForm.getStatus())
                .insertTime(new Date(System.currentTimeMillis()))
                .updateTime(new Date(System.currentTimeMillis()))
                .build();
        return locationRepository.save(location);
    }

    public void deleteLocation(String locationId){
        Optional<Location> locationFromId = locationRepository.findById(Integer.parseInt(locationId));
        if(!locationFromId.isPresent()) {
            throw new RecordNotFoundException(errorMessage.getRecordNotFound());
        }
        locationRepository.delete(locationFromId.get());
    }

    public void updateLocation(String locationId, LocationForm locationForm){
        Optional<Location> locationFromId = locationRepository.findById(Integer.parseInt(locationId));
        if(!locationFromId.isPresent()) {
            throw new RecordNotFoundException(errorMessage.getRecordNotFound());
        }
        Location location = Location.builder().locationId(Integer.parseInt(locationId))
                .locationCode(locationForm.getLocationCode())
                .locationName(locationForm.getLocationName())
                .description(locationForm.getDescription())
                .parentId(locationForm.getParentId())
                .locationLevelId(locationForm.getLocationLevelId())
                .status(locationForm.getStatus())
                .insertTime(locationFromId.get().getInsertTime())
                .updateTime(new Date(System.currentTimeMillis()))
                .build();
        locationRepository.save(location);
    }

    public Location findLocationById(String locationId) {
        Location location = locationRepository.getLocationByLocationId(Integer.parseInt(locationId));
        if (location == null) {
            throw new RecordNotFoundException(errorMessage.getRecordNotFound());
        }
        return location;
    }

    public DataTable findAllPagingAndSorting(Specification<Location> specs, Pageable pageable){
        Page<Location> locations = locationRepository.findAll(specs, pageable);
        return new DataTable(locations);
    }

}
