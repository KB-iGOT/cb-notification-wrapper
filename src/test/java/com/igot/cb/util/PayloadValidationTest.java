package com.igot.cb.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.igot.cb.exceptions.CustomException;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;

import java.io.InputStream;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PayloadValidationTest {

    private PayloadValidation payloadValidation;
    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        JsonSchemaFactory schemaFactory = mock(JsonSchemaFactory.class);
        payloadValidation = new PayloadValidation(schemaFactory);
        mapper = new ObjectMapper();
    }

    @Test
    void testValidatePayload_validObject() throws Exception {
        JsonNode payload = mapper.readTree("{\"name\":\"John\"}");
        JsonSchema schema = mock(JsonSchema.class);
        when(schema.validate(any(JsonNode.class))).thenReturn(Collections.emptySet());
        JsonSchemaFactory schemaFactorytest = mock(JsonSchemaFactory.class);
        when(schemaFactorytest.getSchema(any(InputStream.class))).thenReturn(schema);
        PayloadValidation payloadValidationtest = new PayloadValidation(schemaFactorytest);
        payloadValidationtest.validatePayload("/valid-schema.json", payload);
        verify(schema).validate(payload);
    }


    @Test
    void testValidatePayload_validArray_realSchema() throws Exception {
        JsonNode payload = mapper.readTree("[{\"name\":\"John\"}, {\"name\":\"Jane\"}]");
        PayloadValidation payloadValidationtest = new PayloadValidation(
                JsonSchemaFactory.builder(JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7))
                        .objectMapper(new ObjectMapper())
                        .build()
        );
        payloadValidationtest.validatePayload("/valid-schema.json", payload);
    }

    @Test
    void testValidatePayload_invalidObject() throws Exception {
        JsonNode payload = mapper.readTree("{\"name\":123}");
        JsonSchema schema = mock(JsonSchema.class);
        ValidationMessage mockMessage = mock(ValidationMessage.class);
        when(mockMessage.getMessage()).thenReturn("Type mismatch");
        Set<ValidationMessage> errors = Set.of(mockMessage);
        when(schema.validate(any(JsonNode.class))).thenReturn(errors);
        JsonSchemaFactory schemaFactorytest = mock(JsonSchemaFactory.class);
        when(schemaFactorytest.getSchema(any(InputStream.class))).thenReturn(schema);
        PayloadValidation payloadValidationtest = new PayloadValidation(schemaFactorytest);
        CustomException ex = assertThrows(CustomException.class, () ->
                payloadValidationtest.validatePayload("/valid-schema.json", payload));

        assertEquals(HttpStatus.BAD_REQUEST, ex.getHttpStatusCode());
        assertTrue(ex.getMessage().contains("Type mismatch"));
    }

    @Test
    void testValidatePayload_invalidArray() throws Exception {
        JsonNode payload = mapper.readTree("[{\"name\":123}]");
        JsonSchemaFactory schemaFactory = mock(JsonSchemaFactory.class);
        JsonSchema schema = mock(JsonSchema.class);
        ValidationMessage mockMessage = mock(ValidationMessage.class);
        when(mockMessage.getMessage()).thenReturn("Type mismatch");
        Set<ValidationMessage> errors = new HashSet<>();
        errors.add(mockMessage);
        when(schemaFactory.getSchema(any(InputStream.class))).thenReturn(schema);
        when(schema.validate(any(JsonNode.class))).thenReturn(errors);
        try (var mocked = Mockito.mockStatic(JsonSchemaFactory.class)) {
            mocked.when(JsonSchemaFactory::getInstance).thenReturn(schemaFactory);
            CustomException ex = assertThrows(CustomException.class, () ->
                    payloadValidation.validatePayload("/valid-schema.json", payload));
            assertEquals(HttpStatus.BAD_REQUEST, ex.getHttpStatusCode());
        }
    }



    @Test
    void testValidatePayload_schemaFileNotFound() throws Exception {
        JsonNode payload = mapper.readTree("{\"name\":\"John\"}");
        JsonSchemaFactory schemaFactory = mock(JsonSchemaFactory.class);
        when(schemaFactory.getSchema(any(InputStream.class))).thenThrow(new RuntimeException("File not found"));
        try (var mocked = Mockito.mockStatic(JsonSchemaFactory.class)) {
            mocked.when(JsonSchemaFactory::getInstance).thenReturn(schemaFactory);
            CustomException ex = assertThrows(CustomException.class, () ->
                    payloadValidation.validatePayload("/non-existent-schema.json", payload));
            assertEquals("Failed to validate payload", ex.getCode());
        }
    }

    @Test
    void testValidatePayload_exceptionDuringValidation() throws Exception {
        JsonNode payload = mapper.readTree("{\"name\":\"John\"}");
        JsonSchemaFactory schemaFactory = mock(JsonSchemaFactory.class);
        when(schemaFactory.getSchema(any(InputStream.class))).thenThrow(new RuntimeException("Unexpected error"));
        try (var mocked = Mockito.mockStatic(JsonSchemaFactory.class)) {
            mocked.when(JsonSchemaFactory::getInstance).thenReturn(schemaFactory);
            assertThrows(CustomException.class, () ->
                    payloadValidation.validatePayload("/valid-schema.json", payload));
        }
    }
}