package com.zoctan.api.model;

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

    /**
     * 获取收藏夹Id
     *
     * @return favorite_id - 收藏夹Id
     */
    public Long getFavoriteId() {
        return this.favoriteId;
    }

    /**
     * 设置收藏夹Id
     *
     * @param favoriteId 收藏夹Id
     */
    public void setFavoriteId(final Long favoriteId) {
        this.favoriteId = favoriteId;
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