package com.ices.iotconsumer.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ices.iotconsumer.model.SensorData;
import com.ices.iotconsumer.model.SensorDataEntity;
import com.ices.iotconsumer.service.SensorDataConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@ConditionalOnExpression("!T(org.springframework.util.StringUtils).isEmpty('${spring.kafka.bootstrap-servers}')")
public class SensorDataConsumer {

    @Autowired
    private SensorDataConsumerService dataConsumerService;

    @KafkaListener(topics = "${iot.kafka.topic.sensorTopic}", containerFactory = "kafkaListenerFactory")
    public void consumeAndPersistData(@Payload SensorData sensorData){
        try {
            SensorDataEntity sensorDataEntity = SensorDataEntity.builder()
                    .id(UUID.randomUUID())
                    .sensorType(sensorData.getSensorType())
                    .unit(sensorData.getUnit())
                    .value(sensorData.getValue())
                    .readTime(sensorData.getReadTime())
                    .build();
            dataConsumerService.saveConsumedData(sensorDataEntity);
            log.info("Message consume and save", new ObjectMapper().findAndRegisterModules().writer()
                    .withDefaultPrettyPrinter().writeValueAsString(sensorData));
        }catch (Exception e){
            log.error("Error while consume and save data {} ", e.fillInStackTrace());
        }
    }
}
