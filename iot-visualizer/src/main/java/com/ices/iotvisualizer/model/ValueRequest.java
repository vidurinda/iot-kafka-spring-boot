package com.ices.iotvisualizer.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ValueRequest {
    private ValueType valueType;
    private SensorType sensorType;
}
