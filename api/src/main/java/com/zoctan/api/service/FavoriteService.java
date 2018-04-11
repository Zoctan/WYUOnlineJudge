package com.zoctan.api.service;

import com.zoctan.api.core.service.Service;
import com.zoctan.api.model.Favorite;

import java.util.List;

public interface FavoriteService extends Service<Favorite> {
    @Override
    void save(Favorite favorite);

    List<Favorite> findUserFavoriteByUsername(String username);

    List<Favorite> findUserDetailFavoriteByUsername(String username);
}
