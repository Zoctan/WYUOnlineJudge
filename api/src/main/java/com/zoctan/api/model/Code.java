package com.zoctan.api.model;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Zoctan
 */
public class Code {
    /**
     * 代码Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 题目Id
     */
    @Column(name = "problem_id")
    private Long problemId;

    /**
     * 通过状态
     */
    private Boolean status;

    /**
     * 语言
     */
    private String language;

    /**
     * 消耗时间
     */
    @Column(name = "time_used")
    private Float timeUsed;

    /**
     * 消耗内存
     */
    @Column(name = "memory_used")
    private Float memoryUsed;

    /**
     * 提交时间
     */
    @Column(name = "submit_time")
    private Date submitTime;

    /**
     * Base64压缩代码
     */
    private String code;

    /* ---------- 以下字段来自联表查询 ------------*/
    /**
     * userId
     */
    @Transient
    private Long userId;

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Long getProblemId() {
        return this.problemId;
    }

    public void setProblemId(final Long problemId) {
        this.problemId = problemId;
    }

    public Boolean getStatus() {
        return this.status;
    }

    public void setStatus(final Boolean status) {
        this.status = status;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(final String language) {
        this.language = language;
    }

    public Float getTimeUsed() {
        return this.timeUsed;
    }

    public void setTimeUsed(final Float timeUsed) {
        this.timeUsed = timeUsed;
    }

    public Float getMemoryUsed() {
        return this.memoryUsed;
    }

    public void setMemoryUsed(final Float memoryUsed) {
        this.memoryUsed = memoryUsed;
    }

    public Date getSubmitTime() {
        return this.submitTime;
    }

    public void setSubmitTime(final Date submitTime) {
        this.submitTime = submitTime;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(final Long userId) {
        this.userId = userId;
    }
}