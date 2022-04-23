package com.ices.iotproducer.service;

import com.ices.iotproducer.model.SensorData;
import com.ices.iotproducer.model.SensorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

public interface SensorDataProducerService {

    void publishSensorData(SensorData sensorData, SensorType sensorType);
}
