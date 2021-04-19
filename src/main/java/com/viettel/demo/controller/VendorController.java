package com.viettel.demo.controller;

import com.viettel.demo.common.message.SuccessMessage;
import com.viettel.demo.common.response.ObjectResponse;
import com.viettel.demo.model.entity.Vendor;
import com.viettel.demo.model.view.VendorNameView;
import com.viettel.demo.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendors")
public class VendorController {
    @Autowired
    private VendorService vendorService;

    @Autowired
    private SuccessMessage successMessage;

    @GetMapping("/{vendorId}")
    public ResponseEntity<ObjectResponse> getVendor(@PathVariable("vendorId") String vendorId) {
        Vendor vendor = vendorService.findVendorById(vendorId);
        ObjectResponse response = new ObjectResponse(successMessage.getView(), vendor);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/v0")
    public ResponseEntity<ObjectResponse> getVendors() {
        List<VendorNameView> vendors = vendorService.findBy();
        ObjectResponse response = new ObjectResponse(successMessage.getView(), vendors);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
