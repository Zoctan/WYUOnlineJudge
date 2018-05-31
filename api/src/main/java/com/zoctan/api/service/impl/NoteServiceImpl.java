package com.zoctan.api.service.impl;

import com.zoctan.api.core.service.AbstractService;
import com.zoctan.api.mapper.NoteMapper;
import com.zoctan.api.model.Note;
import com.zoctan.api.service.NoteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Zoctan
 * @date 2018/5/31
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class NoteServiceImpl extends AbstractService<Note> implements NoteService {
    @Resource
    private NoteMapper noteMapper;

    @Override
    public List<Note> findNewest() {
        return this.noteMapper.findNewest();
    }

    @Override
    public Note find(final Long noteId) {
        return this.noteMapper.find(noteId);
    }

}
