package com.zoctan.api.mapper;

import com.zoctan.api.core.mapper.MyMapper;
import com.zoctan.api.model.UserContest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Zoctan
 * @date 2018/5/27
 */
public interface UserContestMapper extends MyMapper<UserContest> {
    /**
     * 根据用户名和比赛Id获取比赛
     *
     * @param username  　用户名
     * @param contestId 　比赛Id
     * @return 用户比赛列表
     */
    List<UserContest> findBy(@Param("username") String username, @Param("contestId") Long contestId);

    /**
     * 根据用户名和比赛Id删除
     *
     * @param username  　用户名
     * @param contestId 　比赛Id
     */
    void deleteByUsernameAndContestId(@Param("username") String username, @Param("contestId") Long contestId);
}