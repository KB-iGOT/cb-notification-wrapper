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
    PROFILE_VERIFICATION(NotificationCategory.PROFILE, "A new profile verification request for {userName}."),
    PROFILE_UPDATE(NotificationCategory.PROFILE, "Your profile verification has been  updated."),
    TRANSFER_UPDATE(NotificationCategory.PROFILE, "Your transfer request has been updated."),
    USER_TRANSFER(NotificationCategory.PROFILE,"You have a new transfer request for {userName} from {title}."),
    CONTENT_SHARE(NotificationCategory.LEARN,"{userName} shared the content {title} with you."),
    TAGGED_COMMENT(NotificationCategory.DISCUSSION,"{userName} mentioned you in their comment or reply."),
    TAGGED_POST(NotificationCategory.DISCUSSION,"{userName}  mentioned you in their post."),
    EVENT_PUBLISHED(NotificationCategory.EVENT,"New event '{title}' has been published"),
    EVENT_ENROLLED(NotificationCategory.EVENT,"{userName} has enrolled in your event '{title}' scheduled on {Date}."),
    COURSE_PUBLISHED(NotificationCategory.LEARN,"New course '{title}' has been published."),
    PROGRAM_PUBLISHED(NotificationCategory.LEARN,"New program {title} is now live"),
    LEARN_DISCUSSION_POST_COMMENT(NotificationCategory.DISCUSSION,"New post added to your course {title}"),
    LEARN_DISCUSSION_POST_REPLY(NotificationCategory.DISCUSSION,"New reply added to the course {title}"),
    PROFANITY_CHECK(NotificationCategory.DISCUSSION,"Your post \"{title}\" has been deleted as it violated our community guidelines"),
    DELETED_BATCH(NotificationCategory.LEARN,"You are no longer part of the {batch} in {program}."),
    BP_ASSIGNMENT_UPLOAD(NotificationCategory.LEARN,"Your Instructor has uploaded a new assignment for your batch: {assignmentTitle}. Please review and submit."),
    BP_ASSIGNMENT_EVALUATE(NotificationCategory.LEARN,"Your assignment for {assignmentTitle} has been evaluated. You can now view your feedback and score in the portal."),
    BP_ASSIGNMENT_SUBMIT(NotificationCategory.CONTENT,"Learner {learnerName} has submitted the assignment {assignmentTitle} for your review."),
    INSTRUCTOR_ADD_BATCH(NotificationCategory.CONTENT, "You have been added as an Instructor to “{programName}”."),
    RETIRE_SCHEDULED(NotificationCategory.CONTENT,"This is to inform you that the course “{title}” has been scheduled for retirement and will be retired on {Date}."),
    PUBLISHED_NEW_VERSION(NotificationCategory.CONTENT,"A new version of the course “{title}” has been successfully published on the platform."),
    RETIRED(NotificationCategory.CONTENT, "This is to formally notify you that the course “{title}” has been successfully retired and is no longer available for consumption on the platform." +
            "Access to this course version has now been closed.");


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

