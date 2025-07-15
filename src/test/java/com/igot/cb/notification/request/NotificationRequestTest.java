package com.igot.cb.notification.request;

import com.igot.cb.notification.enums.*;
import com.igot.cb.notification.request.NotificationRequest.NotificationMessage;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class NotificationRequestTest {

//    @Test
//    void testAllArgsConstructorAndGettersSetters() {
//        NotificationRequest.NotificationMessage message = new NotificationMessage();
//        message.setBody("Test body");
//        message.setPlaceholders(Map.of("key", "value"));
//        message.setData(Map.of("dataKey", 123));
//
//        NotificationRequest request = new NotificationRequest(
//                NotificationType.EMAIL,
//                List.of("user1", "user2"),
//                "org123",
//                "owner1",
//                message
//        );
//
//        assertEquals(NotificationType.EMAIL, request.getType());
//        assertEquals(List.of("user1", "user2"), request.getUserIds());
//        assertEquals("org123", request.getOrgId());
//        assertEquals("owner1", request.getContentOwner());
//        assertEquals(message, request.getMessage());
//
//        assertEquals("Test body", message.getBody());
//        assertEquals("value", message.getPlaceholders().get("key"));
//        assertEquals(123, message.getData().get("dataKey"));
//    }

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
