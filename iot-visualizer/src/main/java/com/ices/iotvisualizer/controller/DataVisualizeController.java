package com.ices.iotvisualizer.controller;

import com.ices.iotvisualizer.model.ValueRequest;
import com.ices.iotvisualizer.model.ValueResponse;
import com.ices.iotvisualizer.service.SensorReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/data")
public class DataVisualizeController {

    @Autowired
    private SensorReadingService readingService;

    @PostMapping("/sensor-reading")
    public ResponseEntity<ValueResponse> getSensorDataReadings(@RequestBody ValueRequest request){
        try{
            ValueResponse response = readingService.getSensorReadingByDeviceAndType(request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
