package com.zoctan.api.service.impl;

import com.zoctan.api.core.service.AbstractService;
import com.zoctan.api.mapper.FavoriteProblemMapper;
import com.zoctan.api.model.FavoriteProblem;
import com.zoctan.api.service.FavoriteProblemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author Zoctan
 * @date 2018/5/27
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FavoriteProblemServiceImpl extends AbstractService<FavoriteProblem> implements FavoriteProblemService {
    @Resource
    private FavoriteProblemMapper favoriteProblemMapper;

}
