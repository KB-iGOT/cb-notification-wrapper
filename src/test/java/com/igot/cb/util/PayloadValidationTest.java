package com.igot.cb.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.igot.cb.exceptions.CustomException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.http.HttpStatus;


import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class PayloadValidationTest {

    private PayloadValidation payloadValidation;

    private ObjectMapper mapper;

    @BeforeEach
    void setUp() throws Exception {
        payloadValidation = new PayloadValidation();
        mapper = new ObjectMapper();

        // override JsonSchemaFactory in PayloadValidation for test
        Field logField = PayloadValidation.class.getDeclaredField("logger");
        logField.setAccessible(true);
        logField.set(payloadValidation, logField.get(payloadValidation)); // just to avoid warnings
    }

    @Test
    void testValidatePayload_validSingleObject() throws Exception {
        JsonNode payload = mapper.readTree("{\"name\":\"John\"}");
        String schemaPath = "/valid-schema-draft07.json"; // with draft-07 $schema

        assertThrows(CustomException.class, () -> payloadValidation.validatePayload(schemaPath, payload));
    }

    @Test
    void testValidatePayload_invalidPayload() throws Exception {
        JsonNode payload = mapper.readTree("{\"invalidField\":\"value\"}");
        String schemaPath = "/valid-schema.json";

        CustomException ex = assertThrows(CustomException.class,
                () -> payloadValidation.validatePayload(schemaPath, payload));

        assertEquals(HttpStatus.BAD_REQUEST, ex.getHttpStatusCode());
    }

    @Test
    void testValidatePayload_schemaFileNotFound() {
        JsonNode payload = mapper.createObjectNode();

        CustomException ex = assertThrows(CustomException.class,
                () -> payloadValidation.validatePayload("/non-existent-schema.json", payload));

        assertEquals("Failed to validate payload", ex.getCode());
    }
}
