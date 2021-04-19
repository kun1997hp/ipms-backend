package com.viettel.demo.repository;

import com.viettel.demo.model.entity.Network;
import com.viettel.demo.model.view.NetworkNameView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NetworkRepository extends JpaRepository<Network, Integer> {

    List<NetworkNameView> findBy();

    Network getNetworkByNetworkId(int networkId);
}
