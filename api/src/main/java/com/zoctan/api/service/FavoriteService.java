package com.zoctan.api.service;

import com.zoctan.api.core.service.Service;
import com.zoctan.api.model.Favorite;

import java.util.List;

/**
 * @author Zoctan
 * @date 2018/5/27
 */
public interface FavoriteService extends Service<Favorite> {
    /**
     * 保存用户收藏夹
     *
     * @param favorite 收藏夹
     */
    @Override
    void save(Favorite favorite);

    /**
     * 获取用户收藏夹
     *
     * @param username 用户名
     * @return 用户收藏夹
     */
    List<Favorite> findUserFavoriteByUsername(String username);

    /**
     * 获取用户收藏夹，包含具体收藏内容
     *
     * @param username 用户名
     * @return 用户收藏夹
     */
    List<Favorite> findUserDetailFavoriteByUsername(String username);
}
