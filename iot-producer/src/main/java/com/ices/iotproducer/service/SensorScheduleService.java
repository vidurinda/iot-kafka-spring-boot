package com.ices.iotproducer.service;

import com.ices.iotproducer.model.SensorData;
import com.ices.iotproducer.model.SensorType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

@Service
@Slf4j
public class SensorScheduleService {

    @Value("sensor.data.publish.endpoint")
    public String dataPublishEndpoint;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SensorDataProducerService dataProducerService;

    public Runnable publishSensorData() {
        return () ->
        {
            log.info("Producing Sensor data " + Instant.now().toString());
            publishThermoMeterData();
            publishFuelMeterData();
            publishHRMeterData();
        };
    }

    @Async
    public void publishThermoMeterData(){
        int readingValue = ThreadLocalRandom.current().nextInt(9, 58 + 1);
        SensorData thermoMeterData = new SensorData(SensorType.THERMO_METER, String.valueOf(readingValue),
                "F", LocalDateTime.now());
        dataProducerService.publishSensorData(thermoMeterData,SensorType.THERMO_METER);
        //restTemplate.postForEntity(dataPublishEndpoint, thermoMeterData, SensorData.class);
    }

    @Async
    public void publishFuelMeterData(){
        int readingValue = ThreadLocalRandom.current().nextInt(61, 99 + 1);
        SensorData fuelMeterData = new SensorData(SensorType.FUEL_METER, String.valueOf(readingValue),
                "L", LocalDateTime.now());
        dataProducerService.publishSensorData(fuelMeterData,SensorType.FUEL_METER);
        //restTemplate.postForEntity(dataPublishEndpoint, fuelMeterData, SensorData.class);
    }

    @Async
    public void publishHRMeterData(){
        int readingValue = ThreadLocalRandom.current().nextInt(101, 158 + 1);
        SensorData hrMeterData = new SensorData(SensorType.HEART_RATE_METER, String.valueOf(readingValue),
                "Puls", LocalDateTime.now());
        dataProducerService.publishSensorData(hrMeterData,SensorType.HEART_RATE_METER);
        //restTemplate.postForEntity(dataPublishEndpoint, hrMeterData, SensorData.class);
    }

}
