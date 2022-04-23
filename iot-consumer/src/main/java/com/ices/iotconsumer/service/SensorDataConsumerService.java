package com.ices.iotconsumer.service;

import com.ices.iotconsumer.model.SensorDataEntity;

public interface SensorDataConsumerService {
    public void saveConsumedData(SensorDataEntity entity);
}
