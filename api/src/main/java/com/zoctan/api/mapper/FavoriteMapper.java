package com.zoctan.api.mapper;

import com.zoctan.api.core.mapper.MyMapper;
import com.zoctan.api.model.Favorite;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Zoctan
 * @date 2018/5/27
 */
public interface FavoriteMapper extends MyMapper<Favorite> {

    /**
     * 获取用户收藏夹，包含具体收藏内容
     *
     * @param username 用户名
     * @return 用户收藏夹
     */
    List<Favorite> findUserDetailFavoriteByUsername(@Param("username") String username);

    /**
     * 获取用户收藏夹
     *
     * @param username 用户名
     * @return 用户收藏夹
     */
    List<Favorite> findUserFavoriteByUsername(@Param("username") String username);
}