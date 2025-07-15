package com.igot.cb.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.lang.reflect.Field;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ProducerConfigurationTest {

    private ProducerConfiguration producerConfiguration;

    @BeforeEach
    void setUp() throws Exception {
        producerConfiguration = new ProducerConfiguration();

        setPrivateField("springKafkabootstrapAddress", "localhost:9092");
    }

    private void setPrivateField(String fieldName, Object value) throws Exception {
        Field field = ProducerConfiguration.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(producerConfiguration, value);
    }

    @Test
    void testProducerFactory() {
        ProducerFactory<String, String> factory = producerConfiguration.producerFactory();

        assertNotNull(factory);
        assertTrue(factory instanceof DefaultKafkaProducerFactory);

        DefaultKafkaProducerFactory<String, String> defaultFactory = (DefaultKafkaProducerFactory<String, String>) factory;
        Map<String, Object> configs = defaultFactory.getConfigurationProperties();

        assertEquals("localhost:9092", configs.get(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG));
        assertEquals(StringSerializer.class, configs.get(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG));
        assertEquals(StringSerializer.class, configs.get(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG));
    }

    @Test
    void testKafkaTemplate() {
        KafkaTemplate<String, String> kafkaTemplate = producerConfiguration.kafkaTemplate();

        assertNotNull(kafkaTemplate);
        assertNotNull(kafkaTemplate.getProducerFactory());
    }
}
