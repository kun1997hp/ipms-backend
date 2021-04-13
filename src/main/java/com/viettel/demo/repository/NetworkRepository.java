package com.viettel.demo.repository;

import com.viettel.demo.model.entity.Network;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NetworkRepository extends JpaRepository<Network, Integer> {

    List<Network> findAllByNetworkNameEquals(String networkCode);

    Page<Network> findAll(Specification<Network>specs, Pageable pageable);

    Network getNetworkByNetworkId(int networkId);
}
