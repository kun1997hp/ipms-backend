package com.viettel.demo.service;

import com.viettel.demo.common.message.ErrorMessage;
import com.viettel.demo.exception.customexception.RecordNotFoundException;
import com.viettel.demo.model.entity.*;
import com.viettel.demo.model.view.VendorNameView;
import com.viettel.demo.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.util.List;

@Service
public class VendorService {
    @Autowired
    private VendorRepository vendorRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private ErrorMessage errorMessage;

    public Vendor findVendorById(String vendorId) {
        Vendor vendor = vendorRepository.getVendorByVendorId(Integer.parseInt(vendorId));
        if (vendor == null) {
            throw new RecordNotFoundException(errorMessage.getRecordNotFound());
        }
        return vendor;
    }

    public List<VendorNameView> findBy(){
        return vendorRepository.findBy();
    }

}
