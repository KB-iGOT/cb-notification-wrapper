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


    @Test
    void testAllArgsConstructorAndGetters() {
        NotificationMessage message = new NotificationMessage();
        message.setBody("Hello");
        message.setPlaceholders(Map.of("x", "y"));
        message.setData(Map.of("a", 123));
        NotificationRequest request = new NotificationRequest(
                NotificationType.EMAIL,
                NotificationSubType.ALERT,
                NotificationCategory.CONTENT,
                NotificationSubCategory.CONTENT_PUBLISHED,
                NotificationSource.SYSTEM_CREATED,
                List.of(UserRole.CBP_ADMIN),
                List.of("user123"),
                "org789",
                "ownerX",
                message
        );
        assertEquals(NotificationType.EMAIL, request.getType());
        assertEquals(NotificationSubType.ALERT, request.getSubType());
        assertEquals(NotificationCategory.CONTENT, request.getCategory());
        assertEquals(NotificationSubCategory.CONTENT_PUBLISHED, request.getSubCategory());
        assertEquals(NotificationSource.SYSTEM_CREATED, request.getSource());
        assertEquals(List.of(UserRole.CBP_ADMIN), request.getRoles());
        assertEquals(List.of("user123"), request.getUserIds());
        assertEquals("org789", request.getOrgId());
        assertEquals("ownerX", request.getContentOwner());
        assertEquals(message, request.getMessage());
    }


    @Test
    void testEqualsAndHashCode() {
        NotificationMessage message1 = new NotificationMessage();
        message1.setBody("Body");
        NotificationRequest req1 = new NotificationRequest();
        req1.setType(NotificationType.PUSH);
        req1.setMessage(message1);
        NotificationMessage message2 = new NotificationMessage();
        message2.setBody("Body");
        NotificationRequest req2 = new NotificationRequest();
        req2.setType(NotificationType.PUSH);
        req2.setMessage(message2);
        assertEquals(req1, req2);
        assertEquals(req1.hashCode(), req2.hashCode());
        assertNotEquals(new Object(), req1);
    }

    @Test
    void testToStringMethods() {
        NotificationMessage message = new NotificationMessage();
        message.setBody("Body");
        NotificationRequest request = new NotificationRequest();
        request.setType(NotificationType.SMS);
        request.setMessage(message);
        String reqStr = request.toString();
        String msgStr = message.toString();
        assertTrue(reqStr.contains("SMS"));
        assertTrue(msgStr.contains("Body"));
    }

    @Test
    void testEqualsSelfAndNull() {
        NotificationRequest req = new NotificationRequest();
        assertEquals(req, req); // same object
        assertNotEquals(null, req); // compare to null
    }

    @Test
    void testEqualsDifferentValues() {
        NotificationMessage msg1 = new NotificationMessage();
        msg1.setBody("Body1");
        NotificationMessage msg2 = new NotificationMessage();
        msg2.setBody("Body2");
        NotificationRequest req1 = new NotificationRequest();
        req1.setType(NotificationType.PUSH);
        req1.setMessage(msg1);
        NotificationRequest req2 = new NotificationRequest();
        req2.setType(NotificationType.PUSH);
        req2.setMessage(msg2);
        assertNotEquals(req1, req2);
    }

    @Test
    void testNotificationMessageEqualsAndHashCode() {
        NotificationMessage m1 = new NotificationMessage();
        m1.setBody("B1");
        NotificationMessage m2 = new NotificationMessage();
        m2.setBody("B1");
        assertEquals(m1, m2);
        assertEquals(m1.hashCode(), m2.hashCode());
        assertNotEquals(new Object(), m1);
        assertNotEquals(null, m1);
        assertEquals(m1, m1);
    }

    @Test
    void testEqualsRequestWithNullFieldDifference() {
        NotificationRequest r1 = new NotificationRequest();
        r1.setType(null);
        NotificationRequest r2 = new NotificationRequest();
        r2.setType(NotificationType.PUSH);
        assertNotEquals(r1, r2);
        assertNotEquals(r2, r1);
    }

    @Test
    void testEqualsRequestWithDifferentFieldValues() {
        NotificationRequest r1 = new NotificationRequest();
        r1.setType(NotificationType.EMAIL);
        NotificationRequest r2 = new NotificationRequest();
        r2.setType(NotificationType.SMS);
        assertNotEquals(r1, r2);
    }

    @Test
    void testEqualsNotificationMessageNullVsNonNull() {
        NotificationMessage m1 = new NotificationMessage();
        m1.setBody(null);
        NotificationMessage m2 = new NotificationMessage();
        m2.setBody("NonNull");
        assertNotEquals(m1, m2);
    }

    @Test
    void testEqualsNotificationMessageDifferentValues() {
        NotificationMessage m1 = new NotificationMessage();
        m1.setBody("Body1");
        NotificationMessage m2 = new NotificationMessage();
        m2.setBody("Body2");
        assertNotEquals(m1, m2);
    }

    @Test
    void testHashCodeConsistency() {
        NotificationMessage msg = new NotificationMessage();
        msg.setBody("Test");
        int hash1 = msg.hashCode();
        int hash2 = msg.hashCode();
        assertEquals(hash1, hash2);
    }

    @Test
    void testEqualsRequestBothNullFields() {
        NotificationRequest r1 = new NotificationRequest();
        NotificationRequest r2 = new NotificationRequest();
        assertEquals(r1, r2);
    }

    @Test
    void testEqualsMessageBothNullFields() {
        NotificationMessage m1 = new NotificationMessage();
        NotificationMessage m2 = new NotificationMessage();
        assertEquals(m1, m2);
        assertEquals(m1.hashCode(), m2.hashCode());
    }

    @Test
    void testHashCodeWithNullFields() {
        NotificationRequest r = new NotificationRequest();
        int hash1 = r.hashCode();
        int hash2 = r.hashCode();
        assertEquals(hash1, hash2);
    }

    @Test
    void testToStringWithNullFields() {
        NotificationRequest r = new NotificationRequest();
        String str = r.toString();
        assertTrue(str.contains("NotificationRequest"));
    }

    @Test
    void testEqualsWithDifferentClass() {
        NotificationRequest r = new NotificationRequest();
        assertNotEquals("string", r);
    }

    @Test
    void testEqualsDifferentSubType() {
        NotificationRequest r1 = new NotificationRequest();
        r1.setSubType(NotificationSubType.ALERT);
        NotificationRequest r2 = new NotificationRequest();
        r2.setSubType(NotificationSubType.UPDATE);
        assertNotEquals(r1, r2);
    }

    @Test
    void testEqualsDifferentCategory() {
        NotificationRequest r1 = new NotificationRequest();
        r1.setCategory(NotificationCategory.CONTENT);
        NotificationRequest r2 = new NotificationRequest();
        r2.setCategory(NotificationCategory.EVENT);
        assertNotEquals(r1, r2);
    }

    @Test
    void testEqualsDifferentSubCategory() {
        NotificationRequest r1 = new NotificationRequest();
        r1.setSubCategory(NotificationSubCategory.CONTENT_PUBLISHED);
        NotificationRequest r2 = new NotificationRequest();
        r2.setSubCategory(NotificationSubCategory.LIKED_POST);
        assertNotEquals(r1, r2);
    }

    @Test
    void testEqualsDifferentSource() {
        NotificationRequest r1 = new NotificationRequest();
        r1.setSource(NotificationSource.SYSTEM_CREATED);
        NotificationRequest r2 = new NotificationRequest();
        r2.setSource(NotificationSource.USER_CREATED);
        assertNotEquals(r1, r2);
    }

    @Test
    void testEqualsDifferentRoles() {
        NotificationRequest r1 = new NotificationRequest();
        r1.setRoles(List.of(UserRole.CBP_ADMIN));
        NotificationRequest r2 = new NotificationRequest();
        r2.setRoles(List.of());
        assertNotEquals(r1, r2);
    }

    @Test
    void testEqualsDifferentUserIds() {
        NotificationRequest r1 = new NotificationRequest();
        r1.setUserIds(List.of("u1"));
        NotificationRequest r2 = new NotificationRequest();
        r2.setUserIds(List.of("u2"));
        assertNotEquals(r1, r2);
    }

    @Test
    void testEqualsDifferentOrgId() {
        NotificationRequest r1 = new NotificationRequest();
        r1.setOrgId("org1");
        NotificationRequest r2 = new NotificationRequest();
        r2.setOrgId("org2");
        assertNotEquals(r1, r2);
    }

    @Test
    void testEqualsDifferentContentOwner() {
        NotificationRequest r1 = new NotificationRequest();
        r1.setContentOwner("owner1");
        NotificationRequest r2 = new NotificationRequest();
        r2.setContentOwner("owner2");
        assertNotEquals(r1, r2);
    }

    @Test
    void testAllArgsConstructorEquality() {
        NotificationMessage msg1 = new NotificationMessage();
        msg1.setBody("Hello");
        NotificationRequest r1 = new NotificationRequest(
                NotificationType.PUSH,
                NotificationSubType.UPDATE,
                NotificationCategory.EVENT,
                NotificationSubCategory.EVENT_PUBLISHED,
                NotificationSource.USER_CREATED,
                List.of(UserRole.CBP_ADMIN),
                List.of("u1"),
                "org1",
                "owner1",
                msg1
        );
        NotificationMessage msg2 = new NotificationMessage();
        msg2.setBody("Hello");
        NotificationRequest r2 = new NotificationRequest(
                NotificationType.PUSH,
                NotificationSubType.UPDATE,
                NotificationCategory.EVENT,
                NotificationSubCategory.EVENT_PUBLISHED,
                NotificationSource.USER_CREATED,
                List.of(UserRole.CBP_ADMIN),
                List.of("u1"),
                "org1",
                "owner1",
                msg2
        );
        assertEquals(r1, r2);
        assertEquals(r1.hashCode(), r2.hashCode());
    }

    @Test
    void testHashCodeDifference() {
        NotificationRequest r1 = new NotificationRequest();
        r1.setOrgId("org1");
        NotificationRequest r2 = new NotificationRequest();
        r2.setOrgId("org2");
        assertNotEquals(r1.hashCode(), r2.hashCode());
    }

    @Test
    void testToStringWithAllFields() {
        NotificationMessage msg = new NotificationMessage();
        msg.setBody("fullBody");
        msg.setPlaceholders(Map.of("k", "v"));
        msg.setData(Map.of("d", 1));
        NotificationRequest r = new NotificationRequest(
                NotificationType.SMS,
                NotificationSubType.PROMOTIONAL,
                NotificationCategory.PROFILE,
                NotificationSubCategory.PROFILE_UPDATE,
                NotificationSource.SYSTEM_CREATED,
                List.of(UserRole.CBP_ADMIN),
                List.of("u1", "u2"),
                "orgX",
                "ownerX",
                msg
        );
        String str = r.toString();
        assertTrue(str.contains("SMS"));
        assertTrue(str.contains("PROMOTIONAL"));
        assertTrue(str.contains("PROFILE_UPDATE"));
        assertTrue(str.contains("orgX"));
        assertTrue(str.contains("ownerX"));
        assertTrue(str.contains("fullBody"));
    }

    @Test
    void testEqualsSubTypeNullVsNonNull() {
        NotificationRequest r1 = new NotificationRequest();
        r1.setSubType(null);
        NotificationRequest r2 = new NotificationRequest();
        r2.setSubType(NotificationSubType.ALERT);
        assertNotEquals(r1, r2);
        assertNotEquals(r2, r1);
    }

    @Test
    void testEqualsCategoryNullVsNonNull() {
        NotificationRequest r1 = new NotificationRequest();
        r1.setCategory(null);
        NotificationRequest r2 = new NotificationRequest();
        r2.setCategory(NotificationCategory.EVENT);
        assertNotEquals(r1, r2);
    }

    @Test
    void testEqualsSubCategoryNullVsNonNull() {
        NotificationRequest r1 = new NotificationRequest();
        r1.setSubCategory(null);
        NotificationRequest r2 = new NotificationRequest();
        r2.setSubCategory(NotificationSubCategory.EVENT_PUBLISHED);
        assertNotEquals(r1, r2);
    }

    @Test
    void testEqualsSourceNullVsNonNull() {
        NotificationRequest r1 = new NotificationRequest();
        r1.setSource(null);
        NotificationRequest r2 = new NotificationRequest();
        r2.setSource(NotificationSource.SYSTEM_CREATED);
        assertNotEquals(r1, r2);
    }

    @Test
    void testEqualsRolesNullVsNonNull() {
        NotificationRequest r1 = new NotificationRequest();
        r1.setRoles(null);
        NotificationRequest r2 = new NotificationRequest();
        r2.setRoles(List.of(UserRole.CBP_ADMIN));
        assertNotEquals(r1, r2);
    }

    @Test
    void testEqualsUserIdsNullVsNonNull() {
        NotificationRequest r1 = new NotificationRequest();
        r1.setUserIds(null);
        NotificationRequest r2 = new NotificationRequest();
        r2.setUserIds(List.of("u1"));
        assertNotEquals(r1, r2);
    }

    @Test
    void testEqualsOrgIdNullVsNonNull() {
        NotificationRequest r1 = new NotificationRequest();
        r1.setOrgId(null);
        NotificationRequest r2 = new NotificationRequest();
        r2.setOrgId("org1");
        assertNotEquals(r1, r2);
    }

    @Test
    void testEqualsContentOwnerNullVsNonNull() {
        NotificationRequest r1 = new NotificationRequest();
        r1.setContentOwner(null);
        NotificationRequest r2 = new NotificationRequest();
        r2.setContentOwner("owner1");
        assertNotEquals(r1, r2);
    }

    @Test
    void testNotificationMessageDifferentMaps() {
        NotificationMessage m1 = new NotificationMessage();
        m1.setBody("B");
        m1.setPlaceholders(Map.of("k", "v"));
        NotificationMessage m2 = new NotificationMessage();
        m2.setBody("B");
        m2.setPlaceholders(Map.of("x", "y"));
        assertNotEquals(m1, m2);
        NotificationMessage m3 = new NotificationMessage();
        m3.setBody("B");
        m3.setData(Map.of("a", 1));
        NotificationMessage m4 = new NotificationMessage();
        m4.setBody("B");
        m4.setData(Map.of("b", 2));
        assertNotEquals(m3, m4);
    }

    @Test
    void testEqualsRequestMessageNullVsNonNull() {
        NotificationRequest r1 = new NotificationRequest();
        r1.setMessage(null);
        NotificationRequest r2 = new NotificationRequest();
        NotificationMessage msg = new NotificationMessage();
        msg.setBody("X");
        r2.setMessage(msg);
        assertNotEquals(r1, r2);
        assertNotEquals(r2, r1); // symmetry
    }

    @Test
    void testHashCodeWithNonNullFields() {
        NotificationRequest r = new NotificationRequest();
        r.setType(NotificationType.EMAIL);
        r.setOrgId("org123");
        r.setContentOwner("ownerX");
        int hash1 = r.hashCode();
        r.setUserIds(List.of("u1"));
        int hash2 = r.hashCode();
        assertNotEquals(hash1, hash2);
    }

    @Test
    void testNotificationMessagePlaceholdersNullVsNonNull() {
        NotificationMessage m1 = new NotificationMessage();
        m1.setBody("Body");
        m1.setPlaceholders(null);
        NotificationMessage m2 = new NotificationMessage();
        m2.setBody("Body");
        m2.setPlaceholders(Map.of("k", "v"));
        assertNotEquals(m1, m2);
    }

    @Test
    void testNotificationMessageDataNullVsNonNull() {
        NotificationMessage m1 = new NotificationMessage();
        m1.setBody("Body");
        m1.setData(null);
        NotificationMessage m2 = new NotificationMessage();
        m2.setBody("Body");
        m2.setData(Map.of("k", 1));
        assertNotEquals(m1, m2);
    }


}
