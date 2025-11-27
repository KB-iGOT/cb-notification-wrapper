package com.igot.cb.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConstantsTest {

    @Test
    void testSomeConstants() {
        // Test HTTP client configuration constants
        assertEquals(45000, Constants.HTTP_CLIENT_TIMEOUT_MS);
        assertEquals(2000, Constants.HTTP_CLIENT_MAX_TOTAL_CONNECTIONS);
        assertEquals(500, Constants.HTTP_CLIENT_MAX_CONNECTIONS_PER_ROUTE);

        // Test API response constants
        assertEquals("1.0", Constants.API_VERSION_1);
        assertEquals("success", Constants.SUCCESS);
        assertEquals("Failed", Constants.FAILED);

        // Test notification constants
        assertEquals("USER_NOTIFICATION", Constants.USER_NOTIFICATION);
        assertEquals("", Constants.EMPTY_STRING);
    }
}
