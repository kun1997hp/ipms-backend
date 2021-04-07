package com.viettel.demo.controller;

import com.viettel.demo.common.DataTable;
import com.viettel.demo.common.message.SuccessMessage;
import com.viettel.demo.common.response.DataTableResponse;
import com.viettel.demo.common.response.MessageResponse;
import com.viettel.demo.common.response.ObjectResponse;
import com.viettel.demo.model.entity.Location;
import com.viettel.demo.model.form.LocationForm;
import com.viettel.demo.service.LocationService;
import net.kaczmarzyk.spring.data.jpa.domain.*;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
    public ResponseEntity<DataTableResponse> getLocations(
            @And({
                    @Spec(path = "locationCode", params = "locationCodeEqual", spec = Equal.class),
                    @Spec(path = "locationName", params = "locationNameLike", spec = Like.class),
                    @Spec(path = "parentId", params = "parentIdEqual", spec = Equal.class),
                    @Spec(path = "locationLevelId", params = "locationLevelIdEqual", spec = Equal.class),
                    @Spec(path = "status", params = "statusEqual", spec = Equal.class),
                    @Spec(path = "insertTime", params = "insertTimeIn", paramSeparator = ',', spec = In.class),
                    @Spec(path = "updateTime", params = "updateTimeIn", paramSeparator = ',', spec = In.class)
            }) Specification<Location> specs, Pageable pageable) {
        DataTable dataTable = locationService.findAllPagingAndSorting(specs, pageable);
        DataTableResponse response = new DataTableResponse(successMessage.getView(), dataTable);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
