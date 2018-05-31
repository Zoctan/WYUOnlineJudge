package com.zoctan.api.service;

import com.zoctan.api.core.service.Service;
import com.zoctan.api.model.Note;

import java.util.List;

/**
 * @author Zoctan
 * @date 2018/5/31
 */
public interface NoteService extends Service<Note> {

    /**
     * 查找最新的笔记
     *
     * @return 最新的笔记列表
     */
    List<Note> findNewest();
}
