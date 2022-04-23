package com.ices.iotvisualizer.service;

import com.ices.iotvisualizer.exception.InvalidParameterException;
import com.ices.iotvisualizer.model.SensorDataEntity;
import com.ices.iotvisualizer.model.ValueRequest;
import com.ices.iotvisualizer.model.ValueResponse;
import com.ices.iotvisualizer.repository.SensorDataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@Slf4j
public class SensorReadingServiceImpl implements SensorReadingService{

    @Autowired
    private SensorDataRepository repository;
    
    @Override
    public ValueResponse getSensorReadingByDeviceAndType(ValueRequest request) throws InvalidParameterException {
        try {
            return calculateSenserRearing(request);
        }catch (Exception e){
            log.error("Error while calculating the sensor readings {} ", e);
            throw new InvalidParameterException("Error while calculating the sensor readings");
        }

    }

    private ValueResponse calculateSenserRearing(ValueRequest request) {
        switch (request.getSensorType()){
            case THERMO_METER: return calculateThermoMeterData(request);
            case HEART_RATE_METER: return calculateHartRateSensorData(request);
            case FUEL_METER: return calculateFuelSensorData(request);
            default: return null;
        }
    }

    private ValueResponse calculateFuelSensorData(ValueRequest request) {
        List<SensorDataEntity> fuelSensorDataList = repository.findAllBySensorType(request.getSensorType());
        String calculatedValue = getCalculatedValue(fuelSensorDataList, request);
        return ValueResponse.builder()
                .sensorType(request.getSensorType())
                .value(calculatedValue)
                .valueType(request.getValueType())
                .build();

    }

    private ValueResponse calculateHartRateSensorData(ValueRequest request) {
        List<SensorDataEntity> hrSensorDataList = repository.findAllBySensorType(request.getSensorType());
        String calculatedValue = getCalculatedValue(hrSensorDataList, request);
        return ValueResponse.builder()
                .sensorType(request.getSensorType())
                .value(calculatedValue)
                .valueType(request.getValueType())
                .build();
    }

    private ValueResponse calculateThermoMeterData(ValueRequest request) {
        List<SensorDataEntity> thermoMeterDataList = repository.findAllBySensorType(request.getSensorType());
        String calculatedValue = getCalculatedValue(thermoMeterDataList, request);
        return ValueResponse.builder()
                .sensorType(request.getSensorType())
                .value(calculatedValue)
                .valueType(request.getValueType())
                .build();
    }

    private String getCalculatedValue(List<SensorDataEntity> sensorDataList, ValueRequest request) {
        if(!CollectionUtils.isEmpty(sensorDataList)){
            switch (request.getValueType()){
                case MIN:return calculateMin(sensorDataList);
                case MAX: return calculateMax(sensorDataList);
                case AVERAGE: return calculateAverage(sensorDataList);
                default:return "0.0";
            }
        }
        return "0.0";

    }

    private String calculateMin(List<SensorDataEntity> sensorDataList) {
        double min = sensorDataList.stream().mapToDouble(
                data -> Double.valueOf(data.getValue())).min().getAsDouble();
        return String.valueOf(min);
    }

    private String calculateMax(List<SensorDataEntity> sensorDataList) {
        double max = sensorDataList.stream().mapToDouble(
                data -> Double.valueOf(data.getValue())).max().getAsDouble();
        return String.valueOf(max);
    }

    private String calculateAverage(List<SensorDataEntity> sensorDataList) {
        double avg = sensorDataList.stream().mapToDouble(
                data -> Double.valueOf(data.getValue())).average().getAsDouble();
        return String.valueOf(avg);
    }
}
