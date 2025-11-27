package com.igot.cb.util;

import org.igot.common.ApiRespParam;
import org.igot.common.ApiResponse;
import org.springframework.http.HttpStatus;
import java.util.*;

/**
 * This class will contains all the common utility methods.
 */
public class ProjectUtil {
    public static ApiResponse createDefaultResponse(String api) {
        ApiResponse response = new ApiResponse();
        response.setId(api);
        response.setVer(Constants.API_VERSION_1);
        response.setParams(new ApiRespParam(UUID.randomUUID().toString()));
        response.getParams().setStatus(Constants.SUCCESS);
        response.setResponseCode(HttpStatus.OK);
        response.setTs(java.time.LocalDateTime.now().toString());
        return response;
    }
}
