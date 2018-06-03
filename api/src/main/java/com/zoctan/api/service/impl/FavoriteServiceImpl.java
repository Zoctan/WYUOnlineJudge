package com.zoctan.api.service.impl;

import com.zoctan.api.core.service.AbstractService;
import com.zoctan.api.mapper.FavoriteMapper;
import com.zoctan.api.mapper.UserFavoriteMapper;
import com.zoctan.api.model.Favorite;
import com.zoctan.api.model.UserFavorite;
import com.zoctan.api.service.FavoriteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Zoctan
 * @date 2018/5/27
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FavoriteServiceImpl extends AbstractService<Favorite> implements FavoriteService {
    @Resource
    private FavoriteMapper favoriteMapper;
    @Resource
    private UserFavoriteMapper userFavoriteMapper;


    @Override
    public void save(final Favorite favorite) {
        this.favoriteMapper.insertSelective(favorite);

        final UserFavorite userFavorite = new UserFavorite();
        userFavorite.setFavoriteId(favorite.getId());
        userFavorite.setUserId(favorite.getUserId());
        this.userFavoriteMapper.insert(userFavorite);
    }

    @Override
    public List<Favorite> findUserFavoriteByUsernameAndClassification(final String username, final Short classification) {
        return this.favoriteMapper.findUserFavoriteByUsernameAndClassification(username, classification);
    }

    @Override
    public List<Favorite> findUserFavoriteProblemByUsername(final String username) {
        return this.favoriteMapper.findUserFavoriteProblemByUsername(username);
    }

    @Override
    public List<Favorite> findUserFavoriteNoteByUsername(final String username) {
        return this.favoriteMapper.findUserFavoriteNoteByUsername(username);
    }
}
