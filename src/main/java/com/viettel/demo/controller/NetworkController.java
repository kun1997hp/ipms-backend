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

    @GetMapping("/networkTypes")
    public ResponseEntity<ObjectResponse> getNetworkTypes() {
        List<NetworkNameView> networks = networkService.findByParentIdIsNull();
        ObjectResponse response = new ObjectResponse(successMessage.getView(), networks);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/networkClasses/")
    public ResponseEntity<ObjectResponse> getNetworkClasses() {
        List<NetworkNameView> networks = networkService.findByParentIdIsNotNull();
        ObjectResponse response = new ObjectResponse(successMessage.getView(), networks);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/networkClasses/{networkTypeId}")
    public ResponseEntity<ObjectResponse> getNetworkClasses(@PathVariable("networkTypeId") String networkTypeId) {
        List<NetworkNameView> networks = networkService.findByParentId(networkTypeId);
        ObjectResponse response = new ObjectResponse(successMessage.getView(), networks);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
