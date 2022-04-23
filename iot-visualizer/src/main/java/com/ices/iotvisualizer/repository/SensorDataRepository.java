package com.ices.iotvisualizer.repository;

import com.ices.iotvisualizer.model.SensorDataEntity;
import com.ices.iotvisualizer.model.SensorType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SensorDataRepository extends MongoRepository<SensorDataEntity, UUID> {

    List<SensorDataEntity> findAllBySensorType(@Param("sensorType")SensorType sensorType);

}
