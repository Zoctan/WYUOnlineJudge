package com.zoctan.api.model;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
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
}