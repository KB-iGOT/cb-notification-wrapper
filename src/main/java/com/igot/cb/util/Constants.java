package com.igot.cb.util;

/**
 * @author Mahesh RV
 */
public class Constants {

    // API Response constants
    public static final String API_VERSION_1 = "1.0";
    public static final String SUCCESS = "success";
    public static final String FAILED = "Failed";
    public static final String RESPONSE = "response";
    public static final String RESULT = "result";
    public static final String CONTENT = "content";

    // HTTP Client Configuration
    public static final int HTTP_CLIENT_TIMEOUT_MS = 45000;
    public static final int HTTP_CLIENT_MAX_TOTAL_CONNECTIONS = 2000;
    public static final int HTTP_CLIENT_MAX_CONNECTIONS_PER_ROUTE = 500;

    // User Service constants
    public static final String BASE_SEARCH_URL = "https://dev.karmayogibharat.net";
    public static final String USER_SEARCH_ENDPOINT = "/api/private/user/v1/search";
    public static final String AUTHORIZATION = "authorization";

    // Notification constants
    public static final String USER_NOTIFICATION = "USER_NOTIFICATION";
    public static final String USER_ID = "user_id";
    public static final String USER_IDS = "user_ids";
    public static final String USER_NAME = "userName";
    public static final String USERID = "userId";
    public static final String FIRST_NAME = "firstName";
    public static final String TYPE = "type";
    public static final String SUB_TYPE = "sub_type";
    public static final String CATEGORY = "category";
    public static final String SUB_CATEGORY = "sub_category";
    public static final String SOURCE = "source";
    public static final String MESSAGE = "message";
    public static final String DATA = "data";
    public static final String BODY = "body";
    public static final String REQUEST = "request";
    public static final String FILTERS = "filters";
    public static final String ROOT_ORG_ID = "rootOrgId";
    public static final String ORGANISATIONS_ROLES = "organisations.roles";
    public static final String EMPTY_STRING = "";
    public static final String PARSE_ERROR = "PARSE_ERROR";

    // Date format
    public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    private Constants() {
    }
}
