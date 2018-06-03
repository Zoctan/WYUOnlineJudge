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
     * 0 Accepted: 成功运行并得到了正确的结果
     * 1 Run Successfully: 运行成功但是不可预知的错误发生
     * 2 Wrong Answer: 成功运行但到得了错误的结果
     * 3 System Error: 设置错误或其他问题
     * 4 Dangerous System Call: 程序因为危险的系统调用被终止
     * 5 Runtime Error: 程序因为类似于堆栈溢出的错误而被系统终止，即运行错误
     * 6 Compile Error: 源代码无法被正确编译
     * 7 Time Limit Exceed: 超过时间限制
     * 8 Memory Limit Exceed: 超过内存限制
     * 9 Output Limit Exceed: 程序输出长度超过答案长度
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