package com.ices.iotvisualizer.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ValueResponse {
    private ValueType valueType;
    private SensorType sensorType;
    private String value;
}
