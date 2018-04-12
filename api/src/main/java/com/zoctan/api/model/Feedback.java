package com.zoctan.api.model;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Data
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
}