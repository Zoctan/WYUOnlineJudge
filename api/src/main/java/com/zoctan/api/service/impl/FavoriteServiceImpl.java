package com.zoctan.api.service.impl;

import com.zoctan.api.core.service.AbstractService;
import com.zoctan.api.mapper.FavoriteMapper;
import com.zoctan.api.model.Favorite;
import com.zoctan.api.service.FavoriteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
@SuppressWarnings("SpringJavaAutowiringInspection")
public class FavoriteServiceImpl extends AbstractService<Favorite> implements FavoriteService {
    @Resource
    private FavoriteMapper favoriteMapper;

    @Override
    public List<Favorite> findUserFavoriteByUsername(final String username) {
        return this.favoriteMapper.findUserFavoriteByUsername(username);
    }

    @Override
    public List<Favorite> findUserDetailFavoriteByUsername(final String username) {
        return this.favoriteMapper.findUserDetailFavoriteByUsername(username);
    }
}
