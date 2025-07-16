package com.igot.cb.notification.request;

import com.igot.cb.notification.enums.*;
import com.igot.cb.notification.request.NotificationRequest.NotificationMessage;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class NotificationRequestTest {

    @Test
    void testNoArgsConstructorAndSetters() {
        NotificationRequest request = new NotificationRequest();
        NotificationMessage message = new NotificationMessage();

        request.setType(NotificationType.PUSH);
        request.setUserIds(List.of("u1"));
        request.setOrgId("org456");
        request.setContentOwner("owner2");
        request.setMessage(message);

        message.setBody("Body text");
        message.setPlaceholders(Map.of());
        message.setData(Map.of());

        assertEquals(NotificationType.PUSH, request.getType());
        assertEquals(List.of("u1"), request.getUserIds());
        assertEquals("org456", request.getOrgId());
        assertEquals("owner2", request.getContentOwner());
        assertEquals(message, request.getMessage());
        assertEquals("Body text", message.getBody());
    }
}
