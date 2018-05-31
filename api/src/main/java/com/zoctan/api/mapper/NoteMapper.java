package com.zoctan.api.mapper;

import com.zoctan.api.core.mapper.MyMapper;
import com.zoctan.api.model.Note;

import java.util.List;

/**
 * @author Zoctan
 * @date 2018/5/31
 */
public interface NoteMapper extends MyMapper<Note> {
    /**
     * 查找最新的笔记
     *
     * @return 最新的笔记列表
     */
    List<Note> findNewest();
}