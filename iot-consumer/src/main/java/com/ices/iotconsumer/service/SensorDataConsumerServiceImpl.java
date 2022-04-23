package com.ices.iotconsumer.service;

import com.ices.iotconsumer.model.SensorDataEntity;
import com.ices.iotconsumer.repository.SensorDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SensorDataConsumerServiceImpl implements SensorDataConsumerService {
    @Autowired
    private SensorDataRepository sensorDataRepository;

    @Override
    @Transactional
    public void saveConsumedData(SensorDataEntity entity) {
        sensorDataRepository.save(entity);
    }
}
