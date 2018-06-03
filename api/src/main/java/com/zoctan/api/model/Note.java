package com.zoctan.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Zoctan
 * @date 2018/5/31
 */
public class Note {
    /**
     * 笔记Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 创建用户Id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 标题
     */
    private String title;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /* ---------- 以下字段来自联表查询 ------------*/

    /**
     * 用户名
     */
    @Transient
    private String username;

    /**
     * 获取笔记Id
     *
     * @return id - 笔记Id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * 设置笔记Id
     *
     * @param id 笔记Id
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * 获取标题
     *
     * @return title - 标题
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(final String title) {
        this.title = title;
    }

    /**
     * 获取描述
     *
     * @return description - 描述
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * 设置描述
     *
     * @param description 描述
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(final Date createTime) {
        this.createTime = createTime;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(final Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }
}