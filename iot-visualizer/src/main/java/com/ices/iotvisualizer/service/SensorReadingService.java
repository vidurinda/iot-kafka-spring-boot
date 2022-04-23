package com.ices.iotvisualizer.service;

import com.ices.iotvisualizer.exception.InvalidParameterException;
import com.ices.iotvisualizer.model.ValueRequest;
import com.ices.iotvisualizer.model.ValueResponse;

public interface SensorReadingService {

    public ValueResponse getSensorReadingByDeviceAndType(ValueRequest request) throws InvalidParameterException;
}
