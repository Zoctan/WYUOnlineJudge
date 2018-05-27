package com.zoctan.api.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Zoctan
 * @date 2018/5/27
 */
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

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(final Long userId) {
        this.userId = userId;
    }

    public Long getCodeId() {
        return this.codeId;
    }

    public void setCodeId(final Long codeId) {
        this.codeId = codeId;
    }
}