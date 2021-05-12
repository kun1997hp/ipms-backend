package com.viettel.demo.controller;

import com.viettel.demo.common.message.SuccessMessage;
import com.viettel.demo.common.response.ObjectResponse;
import com.viettel.demo.model.entity.FaultLevel;
import com.viettel.demo.model.view.FaultLevelNameView;
import com.viettel.demo.service.FaultLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/faultLevels")
public class FaultLevelController {
    @Autowired
    private FaultLevelService faultLevelService;

    @Autowired
    private SuccessMessage successMessage;

    @GetMapping("/{faultLevelId}")
    public ResponseEntity<ObjectResponse> getFaultLevel(@PathVariable("faultLevelId") String faultLevelId) {
        FaultLevel faultLevel = faultLevelService.findFaultLevelById(faultLevelId);
        ObjectResponse response = new ObjectResponse(successMessage.getView(), faultLevel);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/v0")
    public ResponseEntity<ObjectResponse> getFaultLevels() {
        List<FaultLevelNameView> faultLevels = faultLevelService.findBy();
        ObjectResponse response = new ObjectResponse(successMessage.getView(), faultLevels);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
