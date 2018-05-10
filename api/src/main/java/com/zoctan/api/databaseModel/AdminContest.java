package com.zoctan.api.databaseModel;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "admin_contest")
public class AdminContest {
    /**
     * 举办者Id
     */
    @Id
    @Column(name = "user_id")
    private Long userId;

    /**
     * 比赛Id
     */
    @Column(name = "contest_id")
    private Long contestId;

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(final Long userId) {
        this.userId = userId;
    }

    public Long getContestId() {
        return this.contestId;
    }

    public void setContestId(final Long contestId) {
        this.contestId = contestId;
    }
}