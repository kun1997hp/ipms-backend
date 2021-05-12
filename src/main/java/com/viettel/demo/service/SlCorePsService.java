package com.viettel.demo.service;

import com.viettel.demo.common.DataTable;
import com.viettel.demo.common.message.ErrorMessage;
import com.viettel.demo.exception.customexception.RecordNotFoundException;
import com.viettel.demo.model.entity.Device;
import com.viettel.demo.model.entity.SlCorePs;
import com.viettel.demo.model.form.DeviceForm;
import com.viettel.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.util.Date;
import java.util.Optional;

@Service
public class SlCorePsService {
    @Autowired
    private SlCorePsRepository slCorePsRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private ErrorMessage errorMessage;
    public DataTable findAllPagingAndSorting(Specification<SlCorePs> specs, Pageable pageable){
        Page<SlCorePs> slCorePss = slCorePsRepository.findAll(specs, pageable);
        return new DataTable(slCorePss);
    }
}
