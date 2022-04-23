package com.ices.iotproducer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class SensorData {
    private SensorType sensorType;
    private String value;
    private String unit;
    private LocalDateTime readTime;
}
