package com.zoctan.api.core.response;

import org.springframework.http.HttpStatus;

/**
 * 响应结果生成工具
 *
 * @author Zoctan
 * @date 2018/5/27
 */
public class ResultGenerator {
    private static final String DEFAULT_OK_MESSAGE = "OK";
    private static final String DEFAULT_UNAUTHORIZED_MESSAGE = "need token";
    private static final String DEFAULT_METHOD_NOT_ALLOWED_MESSAGE = "request method error";

    public static Result genOkResult() {
        return new Result.Builder(HttpStatus.OK.value())
                .message(DEFAULT_OK_MESSAGE)
                .build();
    }

    public static Result genOkResult(final Object data) {
        return new Result.Builder(HttpStatus.OK.value())
                .message(DEFAULT_OK_MESSAGE)
                .data(data)
                .build();
    }

    public static Result genFailedResult(final String message) {
        return new Result.Builder(HttpStatus.BAD_REQUEST.value())
                .message(message)
                .build();
    }

    public static Result genMethodErrorResult() {
        return new Result.Builder(HttpStatus.METHOD_NOT_ALLOWED.value())
                .message(DEFAULT_METHOD_NOT_ALLOWED_MESSAGE)
                .build();
    }

    public static Result genUnauthorizedResult() {
        return new Result.Builder(HttpStatus.UNAUTHORIZED.value())
                .message(DEFAULT_UNAUTHORIZED_MESSAGE)
                .build();
    }

    public static Result genUnauthorizedResult(final String message) {
        return new Result.Builder(HttpStatus.UNAUTHORIZED.value())
                .message(message)
                .build();
    }

    public static Result genInternalServerErrorResult(final String url) {
        return new Result.Builder(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("API [" + url + "] internal error")
                .build();
    }
}
