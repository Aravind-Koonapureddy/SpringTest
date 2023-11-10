package com.example.demo.controller;

import com.example.demo.model.CustomerRequest;
import com.example.demo.service.DataService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ApiController {

    @Autowired
    public DataService dataService;

    @PostMapping("/combineData")
   @ApiOperation(value = "Combine Pack 1 and Pack 2 data for a customer")
    public ResponseEntity<String> combineData(@RequestBody CustomerRequest request) {

        String combinedData = dataService.combineData(request.getCustomer_id());

        if (combinedData != null) {
            return ResponseEntity.ok(combinedData);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to combine data for the customer");
        }
    }


}
