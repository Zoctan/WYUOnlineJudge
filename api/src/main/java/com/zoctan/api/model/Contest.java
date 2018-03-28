package com.zoctan.api.model;

import java.util.Date;
import javax.persistence.*;

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
    private Boolean join;

    /**
     * 状态：0未开始 | 1已开始 | 2已结束
     */
    private Boolean status;

    /**
     * 密码
     */
    private String password;

    /**
     * 描述
     */
    private String description;

    /**
     * 获取比赛Id
     *
     * @return id - 比赛Id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置比赛Id
     *
     * @param id 比赛Id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取标题
     *
     * @return title - 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取开始时间
     *
     * @return start_time - 开始时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置开始时间
     *
     * @param startTime 开始时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取结束时间
     *
     * @return end_time - 结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置结束时间
     *
     * @param endTime 结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取是否允许加入：0否 | 1是
     *
     * @return join - 是否允许加入：0否 | 1是
     */
    public Boolean getJoin() {
        return join;
    }

    /**
     * 设置是否允许加入：0否 | 1是
     *
     * @param join 是否允许加入：0否 | 1是
     */
    public void setJoin(Boolean join) {
        this.join = join;
    }

    /**
     * 获取状态：0未开始 | 1已开始 | 2已结束
     *
     * @return status - 状态：0未开始 | 1已开始 | 2已结束
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * 设置状态：0未开始 | 1已开始 | 2已结束
     *
     * @param status 状态：0未开始 | 1已开始 | 2已结束
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取描述
     *
     * @return description - 描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置描述
     *
     * @param description 描述
     */
    public void setDescription(String description) {
        this.description = description;
    }
}