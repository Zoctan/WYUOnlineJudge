package com.zoctan.api.service.impl;

import com.zoctan.api.mapper.UserFavoriteMapper;
import com.zoctan.api.databaseModel.UserFavorite;
import com.zoctan.api.service.UserFavoriteService;
import com.zoctan.api.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
* Created by Zoctan on 2018/04/09.
*/
@Service
@Transactional
@SuppressWarnings("SpringJavaAutowiringInspection")
public class UserFavoriteServiceImpl extends AbstractService<UserFavorite> implements UserFavoriteService {
    @Resource
    private UserFavoriteMapper userFavoriteMapper;

}
