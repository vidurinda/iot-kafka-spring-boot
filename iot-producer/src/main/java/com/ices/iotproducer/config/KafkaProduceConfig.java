package com.ices.iotproducer.config;

import com.ices.iotproducer.model.SensorData;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.admin.TopicDescription;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.TopicConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Configuration
@Slf4j
public class KafkaProduceConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${iot.kafka.topic.sensorTopic}")
    private String topicName;

    @Value("${iot.kafka.topic.numOfPartition}")
    private int numOfPartition;

    @Value("${iot.kafka.topic.numOfReplication}")
    private short numOfReplication;

    @Bean
    public Map<String, Object> getProducerConfig(){
        Map<String, Object> producerConfig = new HashMap<>();
        producerConfig.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        producerConfig.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        producerConfig.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return producerConfig;
    }

    @Bean
    public ProducerFactory getProducerFactory(){
        return new DefaultKafkaProducerFactory(getProducerConfig());
    }

    @Bean
    public KafkaTemplate <String, SensorData> getKafkaTemplate(){
        return new KafkaTemplate<String, SensorData>(getProducerFactory());
    }

    @Bean
    @ConditionalOnExpression("!T(org.springframework.util.StringUtils).isEmpty('${spring.kafka.bootstrap-servers:}')")
    public NewTopic sensorDataEvent(KafkaAdmin admin) throws ExecutionException, InterruptedException {
        return initializeTopic(admin, topicName);
    }

    private NewTopic initializeTopic(KafkaAdmin admin, String topicName) throws ExecutionException, InterruptedException {
        NewTopic topic = new NewTopic(topicName, numOfPartition, numOfReplication);
        try (AdminClient client = AdminClient.create(admin.getConfigurationProperties())) {
            log.info("Topics = " + client.listTopics().names().get());
            try {
                Map<String, TopicDescription> map = client.describeTopics(List.of(topicName)).all().get();
                TopicDescription topicDescription = map.get(topicName);
                log.info("Topic for fare [topic={},description={}]", topicName, topicDescription);
            } catch (Exception exception) {
                createTopic(topic, client, topicName);
            }
        }
        return topic;
    }

    private void createTopic(NewTopic topic, AdminClient client, String topicName) {
        log.info("Creating new topic [name={},partitions={},replications={}]", topicName,
                numOfPartition, numOfReplication);
        Map<String, String> configs = new HashMap<>();
        configs.put(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_COMPACT);
        configs.put(TopicConfig.DELETE_RETENTION_MS_CONFIG, "100");
        configs.put(TopicConfig.SEGMENT_MS_CONFIG, "1000");
        configs.put(TopicConfig.MIN_COMPACTION_LAG_MS_CONFIG, "0");
        configs.put(TopicConfig.RETENTION_MS_CONFIG, "-1");
        topic.configs(configs);
        client.createTopics(List.of(topic));
    }

}
