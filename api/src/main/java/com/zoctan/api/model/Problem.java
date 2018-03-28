package com.zoctan.api.model;

import javax.persistence.*;

public class Problem {
    /**
     * 题目Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 难易度
     */
    private Short level;

    /**
     * 标签
     */
    private String tags;

    /**
     * 通过数
     */
    private Long accepted;

    /**
     * 提交数
     */
    private Long submitted;

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
        return id;
    }

    /**
     * 设置题目Id
     *
     * @param id 题目Id
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
     * 获取难易度
     *
     * @return level - 难易度
     */
    public Short getLevel() {
        return level;
    }

    /**
     * 设置难易度
     *
     * @param level 难易度
     */
    public void setLevel(Short level) {
        this.level = level;
    }

    /**
     * 获取标签
     *
     * @return tags - 标签
     */
    public String getTags() {
        return tags;
    }

    /**
     * 设置标签
     *
     * @param tags 标签
     */
    public void setTags(String tags) {
        this.tags = tags;
    }

    /**
     * 获取通过数
     *
     * @return accepted - 通过数
     */
    public Long getAccepted() {
        return accepted;
    }

    /**
     * 设置通过数
     *
     * @param accepted 通过数
     */
    public void setAccepted(Long accepted) {
        this.accepted = accepted;
    }

    /**
     * 获取提交数
     *
     * @return submitted - 提交数
     */
    public Long getSubmitted() {
        return submitted;
    }

    /**
     * 设置提交数
     *
     * @param submitted 提交数
     */
    public void setSubmitted(Long submitted) {
        this.submitted = submitted;
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