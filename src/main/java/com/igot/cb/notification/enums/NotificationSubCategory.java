package com.igot.cb.notification.enums;

public enum NotificationSubCategory {
    CONTENT_REVIEW_REQUEST(NotificationCategory.CONTENT, "New content '{title}' has been submitted for your review"),
    CONTENT_PUBLISHED(NotificationCategory.CONTENT, "Your content '{title}' has been published successfully."),
    CONTENT_SPV_PUBLISHED(NotificationCategory.CONTENT, "New content ‘{Title}’ has been submitted for publishing."),
    CONTENT_REJECTED(NotificationCategory.CONTENT, "Your content '{title}' was not approved. Please check reviewer comments."),
    CONTENT_EDITED(NotificationCategory.CONTENT,"Your content '{title}' was edited by the publisher. Review the changes."),
    LIKED_POST(NotificationCategory.DISCUSSION, "{userName} liked your post."),
    LIKED_COMMENT(NotificationCategory.DISCUSSION, "{userName} commented on your post."),
    REPLIED_POST(NotificationCategory.DISCUSSION, "{userName} liked your reply."),
    POST_COMMENT(NotificationCategory.DISCUSSION ,"{userName} liked on your commented post."),
    REPLIED_COMMENT(NotificationCategory.DISCUSSION,"{userName} replied to your comment."),
    SEND_CONNECTION_REQUEST(NotificationCategory.NETWORK, "{userName} has sent you a connection request."),
    ACCEPTED_CONNECTION_REQUEST(NotificationCategory.NETWORK, "{userName} accepted your connection request."),
    REJECTED_CONNECTION_REQUEST(NotificationCategory.NETWORK, "{userName} rejected your connection request."),
    PROFILE_VERIFICATION(NotificationCategory.PROFILE, "A new profile verification request has been submitted for your review"),
    USER_TRANSFER(NotificationCategory.PROFILE,"You have received a new user transfer request."),
    CONTENT_SHARE(NotificationCategory.LEARN,"{userName} shared the content {title} with you."),
    TAGGED_COMMENT(NotificationCategory.DISCUSSION,"{userName} mentioned you in their comment/reply."),
    TAGGED_POST(NotificationCategory.DISCUSSION,"{userName}  mentioned you in their post (For post tagging).");

    private final NotificationCategory category;
    private final String messageTemplate;

    NotificationSubCategory(NotificationCategory category, String messageTemplate) {
        this.category = category;
        this.messageTemplate = messageTemplate;
    }

    public NotificationCategory getCategory() {
        return category;
    }

    public String getMessageTemplate() {
        return messageTemplate;
    }
}

