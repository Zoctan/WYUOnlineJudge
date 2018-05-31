package com.zoctan.api.service;

import com.zoctan.api.core.service.Service;
import com.zoctan.api.model.Contest;

import java.util.List;

/**
 * @author Zoctan
 * @date 2018/5/27
 */
public interface ContestService extends Service<Contest> {
    /**
     * 获取所有比赛
     *
     * @return 所有比赛
     */
    @Override
    List<Contest> findAll();
    /**
     * 获取热门比赛
     *
     * @return 热门比赛列表
     */
    List<Contest> findHot();

    /**
     * 获取具体的比赛，包含用户参与信息
     *
     * @param contestId 比赛Id
     * @param username  用户名
     * @return 所有比赛
     */
    Contest findOne(Long contestId, String username);
}
