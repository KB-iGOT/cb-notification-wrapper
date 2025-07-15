package com.igot.cb.notification.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.igot.cb.authentication.util.AccessTokenValidator;
import com.igot.cb.notification.enums.*;
import com.igot.cb.notification.request.NotificationRequest;
import com.igot.cb.notification.service.NotificationService;
import com.igot.cb.notification.user.UserService;
import com.igot.cb.producer.Producer;
import com.igot.cb.util.ApiResponse;
import com.igot.cb.util.Constants;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import org.springframework.http.HttpStatus;

import java.lang.reflect.Field;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NotificationServiceImplTest {

    private NotificationServiceImpl service;
    private AccessTokenValidator accessTokenValidator;
    private UserService userService;
    private ObjectMapper mapper;
    private Producer producer;

    @BeforeEach
    void setUp() throws Exception {
        service = new NotificationServiceImpl();

        accessTokenValidator = mock(AccessTokenValidator.class);
        userService = mock(UserService.class);
        mapper = new ObjectMapper();
        producer = mock(Producer.class);

        setPrivateField("accessTokenValidator", accessTokenValidator);
        setPrivateField("userService", userService);
        setPrivateField("mapper", mapper);
        setPrivateField("producer", producer);
        setPrivateField("topicName", "test-topic");
    }

    private void setPrivateField(String fieldName, Object value) throws Exception {
        Field field = NotificationServiceImpl.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(service, value);
    }

    @Test
    void testCreateAndSendNotifications_withUserIds() {
        NotificationRequest request = buildRequestWithUserIds();

        ApiResponse response = service.createAndSendNotifications(request);

        assertEquals(HttpStatus.OK, response.getResponseCode());
        assertNotNull(response.getResult());
        verify(producer, atLeastOnce()).push(eq("test-topic"), any());
    }

    @Test
    void testCreateAndSendNotifications_withOrgSearch() {
        NotificationRequest request = buildRequestWithOrgSearch();

        ApiResponse userSearchResponse = new ApiResponse();
        userSearchResponse.setResponseCode(HttpStatus.OK);
        userSearchResponse.setResult(buildUserSearchResult());
        when(userService.searchUsers(any())).thenReturn(userSearchResponse);

        ApiResponse response = service.createAndSendNotifications(request);

        assertEquals(HttpStatus.OK, response.getResponseCode());
        assertNotNull(response.getResult());
        verify(producer, atLeastOnce()).push(eq("test-topic"), any());
    }

    @Test
    void testCreateAndSendNotifications_withOrgSearchMissingOrgIdOrRoles() {
        NotificationRequest request = buildRequestWithOrgSearch();
        request.setOrgId(null);

        ApiResponse response = service.createAndSendNotifications(request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getResponseCode());
    }

    @Test
    void testCreateAndSendNotifications_withOrgSearchNoUsersFound() {
        NotificationRequest request = buildRequestWithOrgSearch();

        ApiResponse userSearchResponse = new ApiResponse();
        userSearchResponse.setResponseCode(HttpStatus.OK);
        userSearchResponse.setResult(Collections.emptyMap());
        when(userService.searchUsers(any())).thenReturn(userSearchResponse);

        ApiResponse response = service.createAndSendNotifications(request);

        assertEquals(HttpStatus.NOT_FOUND, response.getResponseCode());
    }

    @Test
    void testCreateAndSendNotifications_withNoUserInfo() {
        NotificationRequest request = buildRequestWithNoUserInfo();

        ApiResponse response = service.createAndSendNotifications(request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getResponseCode());
    }

    @Test
    void testCreateAndSendNotifications_withException() {
        NotificationRequest request = buildRequestWithUserIds();
        doThrow(new RuntimeException("kafka error")).when(producer).push(any(), any());

        ApiResponse response = service.createAndSendNotifications(request);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getResponseCode());
    }

    /**
     * Utility methods for building requests & mock responses
     */

    private NotificationRequest buildRequestWithUserIds() {
        NotificationRequest.NotificationMessage message =
                new NotificationRequest.NotificationMessage();

        return new NotificationRequest(
                NotificationType.EMAIL,
                NotificationSubType.PROMOTIONAL,
                NotificationCategory.EVENT,
                NotificationSubCategory.LIKED_COMMENT,
                NotificationSource.SYSTEM_CREATED,
                null,
                List.of("user1", "user2"),
                null,
                null,
                message
        );
    }

    private NotificationRequest buildRequestWithOrgSearch() {
        NotificationRequest.NotificationMessage message =
                new NotificationRequest.NotificationMessage();

        return new NotificationRequest(
                NotificationType.EMAIL,
                NotificationSubType.ALERT,
                NotificationCategory.DISCUSSION,
                NotificationSubCategory.CONTENT_PUBLISHED,
                NotificationSource.SYSTEM_CREATED,
                List.of(UserRole.CBP_ADMIN),
                null,
                "org123",
                null,
                message
        );
    }

    private NotificationRequest buildRequestWithNoUserInfo() {
        NotificationRequest.NotificationMessage message =
                new NotificationRequest.NotificationMessage();

        return new NotificationRequest(
                NotificationType.EMAIL,
                NotificationSubType.ALERT,
                NotificationCategory.CONTENT,
                NotificationSubCategory.CONTENT_PUBLISHED,
                NotificationSource.SYSTEM_CREATED,
                null,
                null,
                null,
                null,
                message
        );
    }

    private Map<String, Object> buildUserSearchResult() {
        Map<String, Object> user = Map.of(
                Constants.USERID, "user123",
                Constants.FIRST_NAME, "John"
        );

        return Map.of(
                Constants.RESULT,
                Map.of(Constants.RESPONSE,
                        Map.of(Constants.CONTENT, List.of(user)))
        );
    }
}
