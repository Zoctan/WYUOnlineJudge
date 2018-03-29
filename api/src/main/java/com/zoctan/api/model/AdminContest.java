package com.zoctan.api.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "admin_contest")
public class AdminContest {
    /**
     * 举办者Id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 比赛Id
     */
    @Id
    @Column(name = "contest_id")
    private Long contestId;

    /**
     * 获取举办者Id
     *
     * @return user_id - 举办者Id
     */
    public Long getUserId() {
        return this.userId;
    }

    /**
     * 设置举办者Id
     *
     * @param userId 举办者Id
     */
    public void setUserId(final Long userId) {
        this.userId = userId;
    }

    /**
     * 获取比赛Id
     *
     * @return contest_id - 比赛Id
     */
    public Long getContestId() {
        return this.contestId;
    }

    /**
     * 设置比赛Id
     *
     * @param contestId 比赛Id
     */
    public void setContestId(final Long contestId) {
        this.contestId = contestId;
    }
}