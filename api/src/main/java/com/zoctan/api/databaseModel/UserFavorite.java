package com.zoctan.api.databaseModel;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

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

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(final Long userId) {
        this.userId = userId;
    }

    public Long getFavoriteId() {
        return this.favoriteId;
    }

    public void setFavoriteId(final Long favoriteId) {
        this.favoriteId = favoriteId;
    }
}