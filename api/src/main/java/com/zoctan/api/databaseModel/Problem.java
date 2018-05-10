package com.zoctan.api.databaseModel;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

    public Short getLevel() {
        return this.level;
    }

    public void setLevel(final Short level) {
        this.level = level;
    }

    public String getTags() {
        return this.tags;
    }

    public void setTags(final String tags) {
        this.tags = tags;
    }

    public Long getAccepted() {
        return this.accepted;
    }

    public void setAccepted(final Long accepted) {
        this.accepted = accepted;
    }

    public Long getSubmitted() {
        return this.submitted;
    }

    public void setSubmitted(final Long submitted) {
        this.submitted = submitted;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }
}