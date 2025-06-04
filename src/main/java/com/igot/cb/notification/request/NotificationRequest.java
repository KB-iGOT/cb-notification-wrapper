package com.igot.cb.notification.request;

import com.igot.cb.notification.enums.*;
import lombok.*;

import java.util.List;
import java.util.Map;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NotificationRequest {
    private NotificationType type;
    private NotificationSubType subType;
    private NotificationCategory category;
    private NotificationSubCategory subCategory;
    private NotificationSource source;
    private List<UserRole> roles;
    private List<String> userIds;
    private String orgId;
    private String contentOwner;
    private NotificationMessage message;

    @Data
    public static class NotificationMessage {
        private String body;
        private Map<String, String> placeholders;
        private Map<String, Object> data;
    }


}
