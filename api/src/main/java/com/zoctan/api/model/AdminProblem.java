package com.zoctan.api.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "admin_problem")
public class AdminProblem {
    /**
     * 出题者Id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 题目Id
     */
    @Id
    @Column(name = "problem_id")
    private Long problemId;

    /**
     * 获取出题者Id
     *
     * @return user_id - 出题者Id
     */
    public Long getUserId() {
        return this.userId;
    }

    /**
     * 设置出题者Id
     *
     * @param userId 出题者Id
     */
    public void setUserId(final Long userId) {
        this.userId = userId;
    }

    /**
     * 获取题目Id
     *
     * @return problem_id - 题目Id
     */
    public Long getProblemId() {
        return this.problemId;
    }

    /**
     * 设置题目Id
     *
     * @param problemId 题目Id
     */
    public void setProblemId(final Long problemId) {
        this.problemId = problemId;
    }
}