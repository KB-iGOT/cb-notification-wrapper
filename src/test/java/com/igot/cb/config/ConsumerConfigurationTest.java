package com.igot.cb.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;

import java.lang.reflect.Field;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ConsumerConfigurationTest {

    private ConsumerConfiguration consumerConfiguration;

    @BeforeEach
    void setUp() throws Exception {
        consumerConfiguration = new ConsumerConfiguration();

        setPrivateField("springKafkabootstrapAddress", "localhost:9092");
        setPrivateField("kafkaOffsetResetValue", "earliest");
        setPrivateField("kafkaMaxPollInterval", 300000);
        setPrivateField("kafkaMaxPollRecords", 500);
        setPrivateField("kafkaAutoCommitInterval", 1000);
    }

    private void setPrivateField(String fieldName, Object value) throws Exception {
        Field field = ConsumerConfiguration.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(consumerConfiguration, value);
    }

    @Test
    void testConsumerConfigs() {
        Map<String, Object> configs = consumerConfiguration.consumerConfigs();

        assertNotNull(configs);
        assertEquals("localhost:9092", configs.get(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG));
        assertEquals(true, configs.get(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG));
        assertEquals("1000", configs.get(ConsumerConfig.FETCH_MAX_WAIT_MS_CONFIG));
        assertEquals(1000, configs.get(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG));
        assertEquals("15000", configs.get(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG));
        assertEquals(StringDeserializer.class, configs.get(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG));
        assertEquals(StringDeserializer.class, configs.get(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG));
        assertEquals("earliest", configs.get(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG));
        assertEquals(300000, configs.get(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG));
        assertEquals(500, configs.get(ConsumerConfig.MAX_POLL_RECORDS_CONFIG));
    }

    @Test
    void testConsumerFactory() {
        ConsumerFactory<String, String> factory = consumerConfiguration.consumerFactory();

        assertNotNull(factory);
        assertEquals(consumerConfiguration.consumerConfigs(), factory.getConfigurationProperties());
    }

    @Test
    void testKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                (ConcurrentKafkaListenerContainerFactory<String, String>)
                        consumerConfiguration.kafkaListenerContainerFactory();

        assertNotNull(factory);
        assertNotNull(factory.getConsumerFactory());
        assertEquals(3000L, factory.getContainerProperties().getPollTimeout());
    }
}
