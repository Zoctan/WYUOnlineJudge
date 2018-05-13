package com.zoctan.api.core.response;

import com.alibaba.fastjson.JSON;

/**
 * 统一API响应结果封装
 *
 * @author Zoctan
 */
public class Result {
    private int status;
    private String message;
    private Object data;

    public Result setStatus(final int status) {
        this.status = status;
        return this;
    }

    public Result setMessage(final String message) {
        this.message = message;
        return this;
    }

    public Result setData(final Object data) {
        this.data = data;
        return this;
    }

    public int getStatus() {
        return this.status;
    }

    public String getMessage() {
        return this.message;
    }

    public Object getData() {
        return this.data;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
