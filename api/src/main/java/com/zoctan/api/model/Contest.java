package com.zoctan.api.model;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Zoctan
 */
public class Contest {
    /**
     * 比赛Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 开始时间
     */
    @Column(name = "start_time")
    private Date startTime;

    /**
     * 结束时间
     */
    @Column(name = "end_time")
    private Date endTime;

    /**
     * 是否允许加入：0否 | 1是
     */
    private Boolean permitted;

    /**
     * 状态：0未开始 | 1已开始 | 2已结束
     */
    private Short status;

    /**
     * 密码
     */
    private String password;

    /**
     * 描述
     */
    private String description;

    /**
     * 参加人数
     */
    @Transient
    private int joinCount;

    /**
     * 当前用户是否参加
     */
    @Transient
    private boolean isJoin;

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

    public Date getStartTime() {
        return this.startTime;
    }

    public void setStartTime(final Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return this.endTime;
    }

    public void setEndTime(final Date endTime) {
        this.endTime = endTime;
    }

    public Boolean getPermitted() {
        return this.permitted;
    }

    public void setPermitted(final Boolean permitted) {
        this.permitted = permitted;
    }

    public Short getStatus() {
        return this.status;
    }

    public void setStatus(final Short status) {
        this.status = status;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public int getJoinCount() {
        return this.joinCount;
    }

    public void setJoinCount(final int joinCount) {
        this.joinCount = joinCount;
    }

    public boolean isJoin() {
        return this.isJoin;
    }

    public void setJoin(final boolean join) {
        this.isJoin = join;
    }
}