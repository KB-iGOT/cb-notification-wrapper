package com.igot.cb.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CbServerPropertiesTest {

    @Test
    void testGettersAndSetters() {
        CbServerProperties props = new CbServerProperties();

        props.setRedisInsightIndex(1);
        props.setSearchResultRedisTtl(600L);
        props.setSbApiKey("test-api-key");
        props.setRequestTimeoutMs(1000);
        props.setMaxTotalConnections(50);
        props.setMaxConnectionsPerRoute(5);
        props.setRedisPoolMaxTotal(20);
        props.setRedisPoolMaxIdle(10);
        props.setRedisPoolMinIdle(2);
        props.setRedisPoolMaxWait(500);
        props.setRedisConnectionTimeout(3000L);

        assertEquals(1, props.getRedisInsightIndex());
        assertEquals(600L, props.getSearchResultRedisTtl());
        assertEquals("test-api-key", props.getSbApiKey());
        assertEquals(1000, props.getRequestTimeoutMs());
        assertEquals(50, props.getMaxTotalConnections());
        assertEquals(5, props.getMaxConnectionsPerRoute());
        assertEquals(20, props.getRedisPoolMaxTotal());
        assertEquals(10, props.getRedisPoolMaxIdle());
        assertEquals(2, props.getRedisPoolMinIdle());
        assertEquals(500, props.getRedisPoolMaxWait());
        assertEquals(3000L, props.getRedisConnectionTimeout());
    }
}
