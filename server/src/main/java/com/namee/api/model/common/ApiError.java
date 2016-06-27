package com.namee.api.model.common;

import com.namee.core.common.util.LogUtil;
import lombok.Data;

/**
 * Created by namee on 2016. 6. 14..
 */
@Data
public class ApiError {
    private ApiErrorType type;
    private ApiErrorCode code;
    private String message;
    private String errorStack;

    public ApiError() {
        type = ApiErrorType.UNKNOWN;
    }

    public ApiError(ApiErrorType type, ApiErrorCode code, String message) {
        this.type = type;
        this.code = code;
        this.message = message;
    }

    public ApiError(ApiErrorType type, ApiErrorCode code, String message, Throwable e) {
        this.type = type;
        this.code = code;
        this.message = message;
        this.errorStack = LogUtil.getStackTrace(e);
    }

    public ApiError(Throwable e) {
        this.type = ApiErrorType.UNKNOWN;
        this.code = ApiErrorCode.UNKNOWN_SERVER_ERROR;
        this.message = e.toString();
        this.errorStack = LogUtil.getStackTrace(e);
    }

    public ApiError(Throwable e, String message, ApiErrorCode code) {
        this.type = ApiErrorType.UNKNOWN;
        this.message = message;
        this.code = code;
        this.message = e.toString();
        this.errorStack = LogUtil.getStackTrace(e);
    }
}
