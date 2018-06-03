package com.zoctan.api.service.impl;

import com.zoctan.api.core.service.AbstractService;
import com.zoctan.api.mapper.FavoriteNoteMapper;
import com.zoctan.api.mapper.FavoriteProblemMapper;
import com.zoctan.api.model.FavoriteNote;
import com.zoctan.api.model.FavoriteProblem;
import com.zoctan.api.service.FavoriteNoteService;
import com.zoctan.api.service.FavoriteProblemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author Zoctan
 * @date 2018/6/3
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FavoriteNoteServiceImpl extends AbstractService<FavoriteNote> implements FavoriteNoteService {
    @Resource
    private FavoriteNoteMapper favoriteNoteMapper;

}
