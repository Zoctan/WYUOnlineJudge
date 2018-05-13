package com.zoctan.api.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Zoctan
 */
@Table(name = "admin_problem")
public class AdminProblem {
    /**
     * 出题者Id
     */
    @Id
    @Column(name = "user_id")
    private Long userId;

    /**
     * 题目Id
     */
    @Column(name = "problem_id")
    private Long problemId;

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(final Long userId) {
        this.userId = userId;
    }

    public Long getProblemId() {
        return this.problemId;
    }

    public void setProblemId(final Long problemId) {
        this.problemId = problemId;
    }
}