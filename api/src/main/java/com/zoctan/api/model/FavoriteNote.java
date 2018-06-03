package com.zoctan.api.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Zoctan
 * @date 2018/6/3
 */
@Table(name = "favorite_note")
public class FavoriteNote {
    /**
     * 收藏夹Id
     */
    @Id
    @Column(name = "favorite_id")
    private Long favoriteId;

    /**
     * 文章Id
     */
    @Column(name = "note_id")
    private Long noteId;

    public Long getFavoriteId() {
        return this.favoriteId;
    }

    public void setFavoriteId(final Long favoriteId) {
        this.favoriteId = favoriteId;
    }

    public Long getNoteId() {
        return noteId;
    }

    public void setNoteId(Long noteId) {
        this.noteId = noteId;
    }
}