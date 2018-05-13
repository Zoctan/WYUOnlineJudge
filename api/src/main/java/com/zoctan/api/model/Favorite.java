package com.zoctan.api.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.List;

/**
 * @author Zoctan
 */
public class Favorite {
    /**
     * 收藏夹Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 收藏夹名称
     */
    private String title;

    /**
     * 是否私有：0否 | 1是
     */
    private Boolean isPrivate;

    /* ---------- 以下字段来自联表查询 ------------*/
    /**
     * 收藏夹下的题目Id
     */
    @Transient
    private List<Long> problemIdList;

    /**
     * 用户Id
     */
    @Transient
    private Long userId;

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public Boolean getPrivate() {
        return this.isPrivate;
    }

    public void setPrivate(final Boolean aPrivate) {
        this.isPrivate = aPrivate;
    }

    public List<Long> getProblemIdList() {
        return this.problemIdList;
    }

    public void setProblemIdList(final List<Long> problemIdList) {
        this.problemIdList = problemIdList;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(final Long userId) {
        this.userId = userId;
    }
}