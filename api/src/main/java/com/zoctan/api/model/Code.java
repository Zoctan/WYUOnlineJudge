package com.zoctan.api.model;

import java.util.Date;
import javax.persistence.*;

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

    /**
     * 获取代码Id
     *
     * @return id - 代码Id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置代码Id
     *
     * @param id 代码Id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取题目Id
     *
     * @return problem_id - 题目Id
     */
    public Long getProblemId() {
        return problemId;
    }

    /**
     * 设置题目Id
     *
     * @param problemId 题目Id
     */
    public void setProblemId(Long problemId) {
        this.problemId = problemId;
    }

    /**
     * 获取通过状态
     *
     * @return status - 通过状态
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * 设置通过状态
     *
     * @param status 通过状态
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * 获取语言
     *
     * @return language - 语言
     */
    public String getLanguage() {
        return language;
    }

    /**
     * 设置语言
     *
     * @param language 语言
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * 获取消耗时间
     *
     * @return time_used - 消耗时间
     */
    public Float getTimeUsed() {
        return timeUsed;
    }

    /**
     * 设置消耗时间
     *
     * @param timeUsed 消耗时间
     */
    public void setTimeUsed(Float timeUsed) {
        this.timeUsed = timeUsed;
    }

    /**
     * 获取消耗内存
     *
     * @return memory_used - 消耗内存
     */
    public Float getMemoryUsed() {
        return memoryUsed;
    }

    /**
     * 设置消耗内存
     *
     * @param memoryUsed 消耗内存
     */
    public void setMemoryUsed(Float memoryUsed) {
        this.memoryUsed = memoryUsed;
    }

    /**
     * 获取提交时间
     *
     * @return submit_time - 提交时间
     */
    public Date getSubmitTime() {
        return submitTime;
    }

    /**
     * 设置提交时间
     *
     * @param submitTime 提交时间
     */
    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    /**
     * 获取Base64压缩代码
     *
     * @return code - Base64压缩代码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置Base64压缩代码
     *
     * @param code Base64压缩代码
     */
    public void setCode(String code) {
        this.code = code;
    }
}