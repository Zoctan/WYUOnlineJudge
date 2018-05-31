package com.zoctan.api.mapper;

import com.zoctan.api.core.mapper.MyMapper;
import com.zoctan.api.model.Contest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Zoctan
 * @date 2018/5/27
 */
public interface ContestMapper extends MyMapper<Contest> {
    /**
     * 获取所有比赛
     *
     * @return 比赛列表
     */
    List<Contest> findAll();

    /**
     * 获取热门比赛
     *
     * @return 热门比赛列表
     */
    List<Contest> findHot();

    /**
     * 获取比赛
     *
     * @param contestId 　比赛Id
     * @return 比赛
     */
    Contest findOne(@Param("contestId") Long contestId);
}