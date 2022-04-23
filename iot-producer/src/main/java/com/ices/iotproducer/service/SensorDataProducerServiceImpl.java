package com.ices.iotproducer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ices.iotproducer.model.SensorData;
import com.ices.iotproducer.model.SensorType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Slf4j
public class SensorDataProducerServiceImpl implements SensorDataProducerService {

    @Value("${iot.kafka.topic.sensorTopic}")
    private String topicName;

    @Autowired
    private KafkaTemplate<String, SensorData> kafkaTemplate;

    @Override
    public void publishSensorData(SensorData sensorData, SensorType sensorType) {
        try {
            kafkaTemplate.send(topicName, sensorType.name(), sensorData);
            log.info("{} Message produced", sensorType.name(),new ObjectMapper().findAndRegisterModules().writer()
                    .withDefaultPrettyPrinter().writeValueAsString(sensorData));
        } catch (JsonProcessingException e) {
            log.error("Error processing json data");
        } catch (Exception ek){
            log.error("Error while producing data");
        }
    }
}
