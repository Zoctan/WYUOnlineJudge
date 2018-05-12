package com.zoctan.api.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class Feedback {
    /**
     * 邮箱
     */
    @NotEmpty(message = "邮箱不能为空")
    @Email
    private String email;

    /**
     * 内容
     */
    @NotEmpty(message = "反馈内容不能为空")
    private String content;

    public String getEmail() {
        return this.email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(final String content) {
        this.content = content;
    }
}