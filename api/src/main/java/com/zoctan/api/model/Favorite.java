package com.zoctan.api.model;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.List;

@Data
public class Favorite {
    /**
     * 收藏夹Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 收藏夹名称
     */
    private String title;

    /**
     * 是否私有：0否 | 1是
     */
    private Boolean isPrivate;

    /* ---------- 以下字段来自联表查询 ------------*/
    /**
     * 收藏夹下的题目Id
     */
    @Transient
    private List<Long> problemIdList;

    /**
     * 用户Id
     */
    @Transient
    private Long userId;
}