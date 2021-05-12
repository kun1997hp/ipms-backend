package com.viettel.demo.service;

import com.viettel.demo.common.message.ErrorMessage;
import com.viettel.demo.exception.customexception.RecordNotFoundException;
import com.viettel.demo.model.entity.*;
import com.viettel.demo.model.view.DeviceTypeNameView;
import com.viettel.demo.model.view.FaultLevelNameView;
import com.viettel.demo.repository.DeviceTypeRepository;
import com.viettel.demo.repository.FaultLevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.util.List;

@Service
public class FaultLevelService {
    @Autowired
    private FaultLevelRepository faultLevelRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private ErrorMessage errorMessage;

    public FaultLevel findFaultLevelById(String faultLevelId) {
        FaultLevel faultLevel = faultLevelRepository.getFaultLevelByFaultLevelId(Integer.parseInt(faultLevelId));
        if (faultLevel == null) {
            throw new RecordNotFoundException(errorMessage.getRecordNotFound());
        }
        return faultLevel;
    }

    public List<FaultLevelNameView> findBy(){
        return faultLevelRepository.findBy();
    }

}
