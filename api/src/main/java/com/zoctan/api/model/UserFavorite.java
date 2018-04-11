package com.zoctan.api.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "user_favorite")
public class UserFavorite {
    /**
     * 用户Id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 收藏夹Id
     */
    @Id
    @Column(name = "favorite_id")
    private Long favoriteId;

    public UserFavorite setUserId(final Long userId) {
        this.userId = userId;
        return this;
    }

    public UserFavorite setFavoriteId(final Long favoriteId) {
        this.favoriteId = favoriteId;
        return this;
    }
}