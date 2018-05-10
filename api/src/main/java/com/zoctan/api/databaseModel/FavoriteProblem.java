package com.zoctan.api.databaseModel;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

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

    public Long getFavoriteId() {
        return this.favoriteId;
    }

    public void setFavoriteId(final Long favoriteId) {
        this.favoriteId = favoriteId;
    }

    public Long getProblemId() {
        return this.problemId;
    }

    public void setProblemId(final Long problemId) {
        this.problemId = problemId;
    }
}