package com.ices.iotconsumer;

import com.ices.iotconsumer.model.SensorDataEntity;
import com.ices.iotconsumer.model.SensorType;
import com.ices.iotconsumer.repository.SensorDataRepository;
import com.ices.iotconsumer.service.SensorDataConsumerServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
class IotConsumerApplicationTests {

    @InjectMocks
    SensorDataConsumerServiceImpl dataConsumerService;

    @Mock
    SensorDataRepository repository;

    @Test
    void contextLoads() {
    }

    @Test
    void saveConsumeDataTest(){
        SensorDataEntity entity = SensorDataEntity.builder()
                .id(UUID.randomUUID())
                .sensorType(SensorType.THERMO_METER)
                .value("100")
                .unit("x")
                .readTime(LocalDateTime.now())
                .build();
        Mockito.when(repository.save(Mockito.any(SensorDataEntity.class))).thenReturn(entity);
        Mockito.when(repository.findById(entity.getId())).thenReturn(Optional.of(entity));
    }

}
