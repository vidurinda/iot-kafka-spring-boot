package com.ices.iotconsumer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class SensorData {
    private SensorType sensorType;
    private String value;
    private String unit;
    private LocalDateTime readTime;
}
