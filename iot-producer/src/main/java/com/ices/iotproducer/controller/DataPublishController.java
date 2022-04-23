package com.ices.iotproducer.controller;

import com.ices.iotproducer.model.SensorData;
import com.ices.iotproducer.service.SensorDataProducerService;
import com.ices.iotproducer.service.SensorScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ScheduledFuture;

@RestController
@RequestMapping("/sensor")
public class DataPublishController {
    public static final long DELAY_RATE = 1000;

    @Autowired
    private TaskScheduler taskScheduler;

    @Autowired
    private SensorScheduleService scheduleService;

    @Autowired
    private SensorDataProducerService producerService;

    ScheduledFuture<?> scheduledFuture;

    @GetMapping("/start")
    public ResponseEntity<String> startProducingData(){
        scheduledFuture = taskScheduler.scheduleAtFixedRate(scheduleService.publishSensorData(),DELAY_RATE);
        return new ResponseEntity<>("Scheduling started", HttpStatus.OK);
    }

    @GetMapping("/stop")
    public ResponseEntity<String> stopProducingData(){
        scheduledFuture.cancel(false);
        return new ResponseEntity<>("Stop producing data", HttpStatus.OK);
    }

    @GetMapping("/test")
    public String test(){
        return "hello";
    }

    @PostMapping(value = "/sensor-data")
    public ResponseEntity<SensorData> publishMessage(SensorData sensorData) {
        producerService.publishSensorData(sensorData, sensorData.getSensorType());
        return new ResponseEntity<SensorData>(sensorData,HttpStatus.OK);
    }
}
