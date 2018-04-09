package com.zoctan.api.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
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
    private Boolean permitted;

    /**
     * 状态：0未开始 | 1已开始 | 2已结束
     */
    private Short status;

    /**
     * 密码
     */
    private String password;

    /**
     * 描述
     */
    private String description;
}