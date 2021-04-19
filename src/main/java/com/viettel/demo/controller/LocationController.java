package com.viettel.demo.controller;

import com.viettel.demo.common.message.SuccessMessage;
import com.viettel.demo.common.response.MessageResponse;
import com.viettel.demo.common.response.ObjectResponse;
import com.viettel.demo.model.entity.Location;
import com.viettel.demo.model.form.LocationForm;
import com.viettel.demo.model.view.LocationNameView;
import com.viettel.demo.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationController {
    @Autowired
    private LocationService locationService;

    @Autowired
    private SuccessMessage successMessage;

    @GetMapping("/{locationId}")
    public ResponseEntity<ObjectResponse> getLocation(@PathVariable("locationId") String locationId) {
        Location location = locationService.findLocationById(locationId);
        ObjectResponse response = new ObjectResponse(successMessage.getView(), location);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ObjectResponse> postLocation(@Valid @RequestBody LocationForm locationForm) {
        Location location = locationService.insertLocation(locationForm);
        ObjectResponse response = new ObjectResponse(successMessage.getAdd(), location);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{locationId}")
    public ResponseEntity<MessageResponse> deleteLocation(@PathVariable("locationId") String locationId) {
        locationService.deleteLocation(locationId);
        return new ResponseEntity<>(new MessageResponse(successMessage.getDelete()), HttpStatus.OK);
    }

    @PutMapping("/{locationId}")
    public ResponseEntity<MessageResponse> putLocation(@PathVariable String locationId, @Valid @RequestBody LocationForm locationForm) {
        locationService.updateLocation(locationId, locationForm);
        return new ResponseEntity<>(new MessageResponse(successMessage.getEdit()), HttpStatus.OK);
    }

    @GetMapping("/v0")
    public ResponseEntity<ObjectResponse> getLocations() {
        List<LocationNameView> locations = locationService.findBy();
        ObjectResponse response = new ObjectResponse(successMessage.getView(), locations);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
