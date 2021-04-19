package com.viettel.demo.controller;

import com.viettel.demo.common.message.SuccessMessage;
import com.viettel.demo.common.response.ObjectResponse;
import com.viettel.demo.model.entity.Network;
import com.viettel.demo.model.view.NetworkNameView;
import com.viettel.demo.service.NetworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/networks")
public class NetworkController {
    @Autowired
    private NetworkService networkService;

    @Autowired
    private SuccessMessage successMessage;

    @GetMapping("/{networkId}")
    public ResponseEntity<ObjectResponse> getNetwork(@PathVariable("networkId") String networkId) {
        Network network = networkService.findNetworkById(networkId);
        ObjectResponse response = new ObjectResponse(successMessage.getView(), network);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/v0")
    public ResponseEntity<ObjectResponse> getNetworks() {
        List<NetworkNameView> networks = networkService.findBy();
        ObjectResponse response = new ObjectResponse(successMessage.getView(), networks);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
