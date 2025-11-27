package com.igot.cb.notification.service;

import org.igot.common.ApiResponse;

import com.igot.cb.notification.request.NotificationRequest;

public interface NotificationService {

    ApiResponse createAndSendNotifications(NotificationRequest request);

}
