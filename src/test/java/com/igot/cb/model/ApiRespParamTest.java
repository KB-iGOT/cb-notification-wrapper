package com.igot.cb.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApiRespParamTest {

    @Test
    void testNoArgsConstructorAndSettersGetters() {
        ApiRespParam param = new ApiRespParam();

        param.setResmsgid("res123");
        param.setMsgid("msg123");
        param.setErr("err");
        param.setStatus("FAILED");
        param.setErrmsg("something went wrong");

        assertEquals("res123", param.getResmsgid());
        assertEquals("msg123", param.getMsgid());
        assertEquals("err", param.getErr());
        assertEquals("FAILED", param.getStatus());
        assertEquals("something went wrong", param.getErrmsg());
    }

    @Test
    void testStringConstructor() {
        ApiRespParam param = new ApiRespParam("test123");

        assertEquals("test123", param.getResmsgid());
        assertEquals("test123", param.getMsgid());
        assertNull(param.getErr());
        assertNull(param.getStatus());
        assertNull(param.getErrmsg());
    }
}
