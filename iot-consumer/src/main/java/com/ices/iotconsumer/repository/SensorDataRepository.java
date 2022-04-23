package com.ices.iotconsumer.repository;

import com.ices.iotconsumer.model.SensorDataEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SensorDataRepository extends MongoRepository<SensorDataEntity, UUID> {
}
