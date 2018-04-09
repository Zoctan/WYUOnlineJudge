package com.zoctan.api.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
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
}