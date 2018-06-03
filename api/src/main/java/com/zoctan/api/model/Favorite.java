package com.zoctan.api.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.List;

/**
 * @author Zoctan
 * @date 2018/5/27
 */
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

    /**
     * 分类：1题目 | 2笔记 | ……
     */
    private Short classification;

    /* ---------- 以下字段来自联表查询 ------------*/
    /**
     * 收藏夹下的题目
     */
    @Transient
    private List<Object> problemList;

    /**
     * 收藏夹下的笔记
     */
    @Transient
    private List<Object> noteList;

    /**
     * 用户Id
     */
    @Transient
    private Long userId;

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

    public Boolean getPrivate() {
        return this.isPrivate;
    }

    public void setPrivate(final Boolean aPrivate) {
        this.isPrivate = aPrivate;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(final Long userId) {
        this.userId = userId;
    }

    public Short getClassification() {
        return this.classification;
    }

    public void setClassification(final Short classification) {
        this.classification = classification;
    }

    public List<Object> getProblemList() {
        return problemList;
    }

    public void setProblemList(List<Object> problemList) {
        this.problemList = problemList;
    }

    public List<Object> getNoteList() {
        return noteList;
    }

    public void setNoteList(List<Object> noteList) {
        this.noteList = noteList;
    }
}