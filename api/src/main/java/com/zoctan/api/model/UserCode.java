package com.zoctan.api.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "user_code")
public class UserCode {
    /**
     * 用户Id
     */
    @Id
    @Column(name = "user_id")
    private Long userId;

    /**
     * 代码Id
     */
    @Column(name = "code_id")
    private Long codeId;

    public UserCode setUserId(final Long userId) {
        this.userId = userId;
        return this;
    }

    public UserCode setCodeId(final Long codeId) {
        this.codeId = codeId;
        return this;
    }
}