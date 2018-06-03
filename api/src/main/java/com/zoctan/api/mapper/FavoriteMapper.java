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
     * 获取用户题目收藏夹
     *
     * @param username 用户名
     * @return 用户收藏夹
     */
    List<Favorite> findUserFavoriteProblemByUsername(@Param("username") String username);

    /**
     * 获取用户笔记收藏夹
     *
     * @param username 用户名
     * @return 用户收藏夹
     */
    List<Favorite> findUserFavoriteNoteByUsername(@Param("username") String username);

    /**
     * 按分类获取用户收藏夹
     *
     * @param username 用户名
     * @return 用户收藏夹
     */
    List<Favorite> findUserFavoriteByUsernameAndClassification(@Param("username") String username, @Param("classification") Short classification);
}