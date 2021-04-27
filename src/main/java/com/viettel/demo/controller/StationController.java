package com.viettel.demo.controller;

import com.viettel.demo.common.message.SuccessMessage;
import com.viettel.demo.common.response.ObjectResponse;
import com.viettel.demo.model.entity.Station;
import com.viettel.demo.model.view.StationCodeView;
import com.viettel.demo.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stations")
public class StationController {
    @Autowired
    private StationService stationService;

    @Autowired
    private SuccessMessage successMessage;

    @GetMapping("/{stationId}")
    public ResponseEntity<ObjectResponse> getStation(@PathVariable("stationId") String stationId) {
        Station station = stationService.findStationById(stationId);
        ObjectResponse response = new ObjectResponse(successMessage.getView(), station);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/v0")
    public ResponseEntity<ObjectResponse> getStations() {
        List<StationCodeView> stations = stationService.findBy();
        ObjectResponse response = new ObjectResponse(successMessage.getView(), stations);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
