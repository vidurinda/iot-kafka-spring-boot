package com.ices.iotvisualizer;

import com.ices.iotvisualizer.model.*;
import com.ices.iotvisualizer.repository.SensorDataRepository;
import com.ices.iotvisualizer.repository.UserRepository;
import com.ices.iotvisualizer.service.SensorReadingService;
import com.ices.iotvisualizer.service.SensorReadingServiceImpl;
import com.ices.iotvisualizer.service.UserServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class IotVisualizerApplicationTests {

    @InjectMocks
    private SensorReadingServiceImpl sensorReadingService;

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    SensorDataRepository repository;

    @Mock
    UserRepository userRepository;



    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getMinSensorReadingTest(){
        List<SensorDataEntity> mockDataList = List.of(
                new SensorDataEntity(UUID.randomUUID(),SensorType.THERMO_METER,"100","x", LocalDateTime.now()),
                new SensorDataEntity(UUID.randomUUID(),SensorType.THERMO_METER,"200","x", LocalDateTime.now()),
                new SensorDataEntity(UUID.randomUUID(),SensorType.THERMO_METER,"300","x", LocalDateTime.now()));

                Mockito.when(repository.findAllBySensorType(SensorType.THERMO_METER)).thenReturn(mockDataList);

        ValueResponse senserReading = sensorReadingService.getSensorReadingByDeviceAndType(
                ValueRequest.builder()
                        .sensorType(SensorType.THERMO_METER)
                        .valueType(ValueType.MIN).build());

        Assert.assertEquals("100.0",senserReading.getValue());
    }

    @Test
    void getAverageSensorReadingTest(){
        List<SensorDataEntity> mockDataList = List.of(
                new SensorDataEntity(UUID.randomUUID(),SensorType.THERMO_METER,"100","x", LocalDateTime.now()),
                new SensorDataEntity(UUID.randomUUID(),SensorType.THERMO_METER,"200","x", LocalDateTime.now()),
                new SensorDataEntity(UUID.randomUUID(),SensorType.THERMO_METER,"300","x", LocalDateTime.now()));

        Mockito.when(repository.findAllBySensorType(SensorType.THERMO_METER)).thenReturn(mockDataList);

        ValueResponse senserReading = sensorReadingService.getSensorReadingByDeviceAndType(
                ValueRequest.builder()
                        .sensorType(SensorType.THERMO_METER)
                        .valueType(ValueType.AVERAGE).build());

        Assert.assertEquals("200.0",senserReading.getValue());
    }

    @Test
    void getMaxSensorReadingTest(){
        List<SensorDataEntity> mockDataList = List.of(
                new SensorDataEntity(UUID.randomUUID(),SensorType.THERMO_METER,"100","x", LocalDateTime.now()),
                new SensorDataEntity(UUID.randomUUID(),SensorType.THERMO_METER,"200","x", LocalDateTime.now()),
                new SensorDataEntity(UUID.randomUUID(),SensorType.THERMO_METER,"300","x", LocalDateTime.now()));

        Mockito.when(repository.findAllBySensorType(SensorType.THERMO_METER)).thenReturn(mockDataList);

        ValueResponse senserReading = sensorReadingService.getSensorReadingByDeviceAndType(
                ValueRequest.builder()
                        .sensorType(SensorType.THERMO_METER)
                        .valueType(ValueType.MAX).build());

        Assert.assertEquals("300.0",senserReading.getValue());
    }

    @Test
    void addUserTest(){
        AppUser user = AppUser.builder()
                .id(UUID.randomUUID())
                .username("TestUser")
                .password("pwd").build();

        Mockito.when(userRepository.save(Mockito.any(AppUser.class))).thenReturn(user);
    }

}
