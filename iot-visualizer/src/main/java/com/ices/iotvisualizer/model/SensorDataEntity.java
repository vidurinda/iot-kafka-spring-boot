package com.ices.iotvisualizer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Document("sensor-data-table")
@Builder
@Data
@AllArgsConstructor
public class SensorDataEntity {
    @Id
    private UUID id;

    private SensorType sensorType;
    private String value;
    private String unit;
    private LocalDateTime readTime;
}
