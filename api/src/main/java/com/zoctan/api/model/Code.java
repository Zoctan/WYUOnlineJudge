package com.zoctan.api.model;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Zoctan
 * @date 2018/5/27
 */
public class Code {
    /**
     * 代码Id
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
     * 题目Id
     */
    @Column(name = "problem_id")
    private Long problemId;

    /**
     * 通过状态
     * 100: 'Accepted',
     * 0: 'Run Successfully',
     * 1: 'CPU Time Limit Exceeded',
     * 2: 'Real Time Limit Exceeded',
     * 3: 'Memory Limit Exceeded',
     * 4: 'Runtime Error',
     * 5: 'System Error',
     * 6: 'Output Limit',
     * 7: 'Presentation Error',
     * 8: 'Compile Error',
     * -1: 'Wrong Answer',
     * -2: 'Error Fork Failed',
     * -3: 'Error Pthread Failed',
     * -4: 'Error Wait Failed',
     * -5: 'Error Root Required',
     * -6: 'Error Load Seccomp Failed',
     * -7: 'Error SetRLimit Failed',
     * -8: 'Error Dup2 Failed',
     * -9: 'Error Set Uid Failed',
     * -10: 'Error Execute Failed',
     * -11: 'Error SPJ'
     */
    private Short status;

    /**
     * 语言
     */
    private String language;

    /**
     * 消耗时间
     */
    @Column(name = "time_used")
    private String timeUsed;

    /**
     * 消耗内存
     */
    @Column(name = "memory_used")
    private String memoryUsed;

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

    /* ---------- 以下字段来自外部查询 ------------*/

    @Transient
    private String error;

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

    public Short getStatus() {
        return this.status;
    }

    public void setStatus(final Short status) {
        this.status = status;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(final String language) {
        this.language = language;
    }

    public String getTimeUsed() {
        return this.timeUsed;
    }

    public void setTimeUsed(final String timeUsed) {
        this.timeUsed = timeUsed;
    }

    public String getMemoryUsed() {
        return this.memoryUsed;
    }

    public void setMemoryUsed(final String memoryUsed) {
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

    public Long getContestId() {
        return this.contestId;
    }

    public void setContestId(final Long contestId) {
        this.contestId = contestId;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}