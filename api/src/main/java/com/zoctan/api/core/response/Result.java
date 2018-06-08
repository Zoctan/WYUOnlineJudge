package com.zoctan.api.core.response;

import com.alibaba.fastjson.JSON;

/**
 * 统一API响应结果封装
 *
 * @author Zoctan
 * @date 2018/5/27
 */
public class Result {
    private int status;
    private String message;
    private Object data;

    private Result(final Builder builder) {
        this.status = builder.status;
        this.message = builder.message;
        this.data = builder.data;
    }

    public static class Builder {
        private final int status;
        private String message;
        private Object data;

        public Builder(final int status) {
            this.status = status;
        }

        public Builder message(final String message) {
            this.message = message;
            return this;
        }

        public Builder data(final Object data) {
            this.data = data;
            return this;
        }

        public Result build() {
            return new Result(this);
        }
    }

    public void setStatus(final int status) {
        this.status = status;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public void setData(final Object data) {
        this.data = data;
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
