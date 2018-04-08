package com.zoctan.api.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Favorite {
    /**
     * 收藏夹Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户Id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 收藏夹名称
     */
    private String title;

    /**
     * 是否私有：0否 | 1是
     */
    private Boolean isPrivate;

    /**
     * 获取收藏夹Id
     *
     * @return id - 收藏夹Id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * 设置收藏夹Id
     *
     * @param id 收藏夹Id
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * 获取用户Id
     *
     * @return user_id - 用户Id
     */
    public Long getUserId() {
        return this.userId;
    }

    /**
     * 设置用户Id
     *
     * @param userId 用户Id
     */
    public void setUserId(final Long userId) {
        this.userId = userId;
    }

    /**
     * 获取收藏夹名称
     *
     * @return title - 收藏夹名称
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * 设置收藏夹名称
     *
     * @param title 收藏夹名称
     */
    public void setTitle(final String title) {
        this.title = title;
    }

    public Boolean getPrivate() {
        return this.isPrivate;
    }

    public void setPrivate(final Boolean aPrivate) {
        this.isPrivate = aPrivate;
    }
}