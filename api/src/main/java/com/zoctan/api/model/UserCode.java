package com.zoctan.api.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

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

    /**
     * 获取用户Id
     *
     * @return user_id - 用户Id
     */
    public Long getUserId() {
        return this.userId;
    }

    /**
     * 设置用户Id
     *
     * @param userId 用户Id
     */
    public void setUserId(final Long userId) {
        this.userId = userId;
    }

    /**
     * 获取代码Id
     *
     * @return code_id - 代码Id
     */
    public Long getCodeId() {
        return this.codeId;
    }

    /**
     * 设置代码Id
     *
     * @param codeId 代码Id
     */
    public void setCodeId(final Long codeId) {
        this.codeId = codeId;
    }
}