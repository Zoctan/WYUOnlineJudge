package com.zoctan.api.service.impl;

import com.zoctan.api.core.service.AbstractService;
import com.zoctan.api.mapper.FavoriteMapper;
import com.zoctan.api.mapper.UserFavoriteMapper;
import com.zoctan.api.model.Favorite;
import com.zoctan.api.model.UserFavorite;
import com.zoctan.api.service.FavoriteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
@SuppressWarnings("SpringJavaAutowiringInspection")
public class FavoriteServiceImpl extends AbstractService<Favorite> implements FavoriteService {
    @Resource
    private FavoriteMapper favoriteMapper;
    @Resource
    private UserFavoriteMapper userFavoriteMapper;


    @Override
    public void save(final Favorite favorite) {
        this.favoriteMapper.insertSelective(favorite);
        this.userFavoriteMapper.insert(new UserFavorite()
                .setFavoriteId(favorite.getId())
                .setUserId(favorite.getUserId()));
    }

    @Override
    public void delete(final Long id) {
        this.favoriteMapper.deleteByPrimaryKey(id);
        final Condition condition = new Condition(UserFavorite.class);
        condition.createCriteria().andCondition("favorite_id = ", id);
        this.userFavoriteMapper.deleteByCondition(condition);
    }

    @Override
    public List<Favorite> findUserFavoriteByUsername(final String username) {
        return this.favoriteMapper.findUserFavoriteByUsername(username);
    }

    @Override
    public List<Favorite> findUserDetailFavoriteByUsername(final String username) {
        return this.favoriteMapper.findUserDetailFavoriteByUsername(username);
    }
}
