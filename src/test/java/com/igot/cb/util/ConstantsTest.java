package com.igot.cb.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConstantsTest {

    @Test
    void testSomeConstants() {
        assertEquals("sunbird", Constants.KEYSPACE_SUNBIRD);
        assertEquals("Unauthorized", Constants.UNAUTHORIZED);
        assertEquals("/payloadValidation/demandValidationData.json", Constants.PAYLOAD_VALIDATION_FILE);
        assertTrue(Constants.ACTIVE_STATUS);
        assertFalse(Constants.ACTIVE_STATUS_FALSE);
        assertEquals(45000, Constants.HTTP_CLIENT_TIMEOUT_MS);
    }
}
