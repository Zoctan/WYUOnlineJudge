package com.zoctan.api.model;

import javax.persistence.*;

/**
 * @author Zoctan
 * @date 2018/5/27
 */
@Table(name = "contest_problem")
public class ContestProblem {
    /**
     * 题目Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 比赛Id
     */
    @Column(name = "contest_id")
    private Long contestId;

    /**
     * 标题
     */
    private String title;

    /**
     * 通过数
     */
    private Long accepted;

    /**
     * 提交数
     */
    private Long submitted;

    /**
     * 当前用户题目是否成功通过
     */
    @Transient
    private boolean currentUserSubmitStatus;

    /**
     * 描述
     */
    private String description;

    /**
     * 获取题目Id
     *
     * @return id - 题目Id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * 设置题目Id
     *
     * @param id 题目Id
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * 获取比赛Id
     *
     * @return contest_id - 比赛Id
     */
    public Long getContestId() {
        return this.contestId;
    }

    /**
     * 设置比赛Id
     *
     * @param contestId 比赛Id
     */
    public void setContestId(final Long contestId) {
        this.contestId = contestId;
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
     * 获取通过数
     *
     * @return accepted - 通过数
     */
    public Long getAccepted() {
        return this.accepted;
    }

    /**
     * 设置通过数
     *
     * @param accepted 通过数
     */
    public void setAccepted(final Long accepted) {
        this.accepted = accepted;
    }

    /**
     * 获取提交数
     *
     * @return submitted - 提交数
     */
    public Long getSubmitted() {
        return this.submitted;
    }

    /**
     * 设置提交数
     *
     * @param submitted 提交数
     */
    public void setSubmitted(final Long submitted) {
        this.submitted = submitted;
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

    public boolean isCurrentUserSubmitStatus() {
        return this.currentUserSubmitStatus;
    }

    public void setCurrentUserSubmitStatus(final boolean currentUserSubmitStatus) {
        this.currentUserSubmitStatus = currentUserSubmitStatus;
    }
}