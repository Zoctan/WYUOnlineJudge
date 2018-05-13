package com.zoctan.api.service.impl;

import com.zoctan.api.core.service.AbstractService;
import com.zoctan.api.mapper.UserFavoriteMapper;
import com.zoctan.api.model.UserFavorite;
import com.zoctan.api.service.UserFavoriteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author Zoctan
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserFavoriteServiceImpl extends AbstractService<UserFavorite> implements UserFavoriteService {
    @Resource
    private UserFavoriteMapper userFavoriteMapper;

}
