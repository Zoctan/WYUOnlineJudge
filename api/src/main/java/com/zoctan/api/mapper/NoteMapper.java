package com.zoctan.api.mapper;

import com.zoctan.api.core.mapper.MyMapper;
import com.zoctan.api.model.Note;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Zoctan
 * @date 2018/5/31
 */
public interface NoteMapper extends MyMapper<Note> {
    /**
     * 获取最新的笔记
     *
     * @return 最新的笔记列表
     */
    List<Note> findNewest();

    /**
     * 获取笔记
     *
     * @param noteId 笔记Id
     * @return 笔记
     */
    Note find(@Param("noteId") Long noteId);
}