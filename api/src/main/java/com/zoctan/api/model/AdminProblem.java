package com.zoctan.api.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
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
}