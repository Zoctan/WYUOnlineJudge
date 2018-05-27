package com.zoctan.api.service;

import com.zoctan.api.core.service.Service;
import com.zoctan.api.model.UserContest;

/**
 * @author Zoctan
 * @date 2018/5/27
 */
public interface UserContestService extends Service<UserContest> {
    /**
     * 保存
     *
     * @param username  用户名
     * @param contestId 比赛Id
     */
    void save(String username, Long contestId);

    /**
     * 删除
     *
     * @param username  用户名
     * @param contestId 比赛Id
     */
    void delete(String username, Long contestId);
}
