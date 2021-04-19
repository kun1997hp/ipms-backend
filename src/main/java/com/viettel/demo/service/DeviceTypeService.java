package com.viettel.demo.service;

import com.viettel.demo.common.message.ErrorMessage;
import com.viettel.demo.exception.customexception.RecordNotFoundException;
import com.viettel.demo.model.entity.*;
import com.viettel.demo.model.view.DeviceTypeNameView;
import com.viettel.demo.repository.DeviceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.util.List;

@Service
public class DeviceTypeService {
    @Autowired
    private DeviceTypeRepository deviceTypeRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private ErrorMessage errorMessage;

    public DeviceType findDeviceTypeById(String deviceTypeId) {
        DeviceType deviceType = deviceTypeRepository.getDeviceTypeByDeviceTypeId(Integer.parseInt(deviceTypeId));
        if (deviceType == null) {
            throw new RecordNotFoundException(errorMessage.getRecordNotFound());
        }
        return deviceType;
    }

    public List<DeviceTypeNameView> findBy(){
        return deviceTypeRepository.findBy();
    }

}
