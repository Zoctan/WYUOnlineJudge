package com.zoctan.api.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "favorite_problem")
public class FavoriteProblem {
    /**
     * 收藏夹Id
     */
    @Id
    @Column(name = "favorite_id")
    private Long favoriteId;

    /**
     * 题目Id
     */
    @Column(name = "problem_id")
    private Long problemId;
}