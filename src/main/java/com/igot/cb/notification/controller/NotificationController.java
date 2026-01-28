package com.igot.cb.notification.controller;

import com.igot.cb.notification.request.NotificationRequest;
import com.igot.cb.notification.service.NotificationService;
import lombok.extern.slf4j.Slf4j;

import org.igot.common.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/notifications")
public class NotificationController {

    NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createNotifications(@RequestBody NotificationRequest request) {
        log.info("Received notification request for create :{}", request);
        ApiResponse response = notificationService.createAndSendNotifications(request);
        return ResponseEntity.status(response.getResponseCode()).body(response);
    }

}
