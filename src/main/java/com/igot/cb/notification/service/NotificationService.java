package com.igot.cb.notification.service;

import com.igot.cb.notification.request.NotificationRequest;
import com.igot.cb.util.ApiResponse;

public interface NotificationService {

    ApiResponse createAndSendNotifications(NotificationRequest request);

}
