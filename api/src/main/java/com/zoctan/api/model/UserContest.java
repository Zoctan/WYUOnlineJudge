package com.zoctan.api.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Zoctan
 * @date 2018/5/27
 */
@Table(name = "user_contest")
public class UserContest {
    /**
     * 用户Id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 收藏夹Id
     */
    @Id
    @Column(name = "contest_id")
    private Long contestId;

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(final Long userId) {
        this.userId = userId;
    }

    public Long getContestIdId() {
        return this.contestId;
    }

    public void setContestIdId(final Long contestId) {
        this.contestId = contestId;
    }
}