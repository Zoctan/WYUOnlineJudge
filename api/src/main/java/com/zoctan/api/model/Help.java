package com.zoctan.api.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Zoctan
 * @date 2018/5/27
 */
public class Help {
    /**
     * 帮助Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 描述
     */
    private String description;

    /**
     * 获取帮助Id
     *
     * @return id - 帮助Id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * 设置帮助Id
     *
     * @param id 帮助Id
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * 获取标题
     *
     * @return title - 标题
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(final String title) {
        this.title = title;
    }

    /**
     * 获取描述
     *
     * @return description - 描述
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * 设置描述
     *
     * @param description 描述
     */
    public void setDescription(final String description) {
        this.description = description;
    }
}