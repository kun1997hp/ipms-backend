package com.viettel.demo.service;

import com.viettel.demo.common.message.ErrorMessage;
import com.viettel.demo.exception.customexception.RecordNotFoundException;
import com.viettel.demo.model.entity.*;
import com.viettel.demo.model.view.NetworkNameView;
import com.viettel.demo.repository.NetworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.util.List;

@Service
public class NetworkService {
    @Autowired
    private NetworkRepository networkRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private ErrorMessage errorMessage;

    public Network findNetworkById(String networkId) {
        Network network = networkRepository.getNetworkByNetworkId(Integer.parseInt(networkId));
        if (network == null) {
            throw new RecordNotFoundException(errorMessage.getRecordNotFound());
        }
        return network;
    }

    public List<NetworkNameView> findBy(){
        return networkRepository.findBy();
    }

}
