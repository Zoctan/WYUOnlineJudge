package com.zoctan.api.mapper;

import com.zoctan.api.core.mapper.MyMapper;
import com.zoctan.api.model.Code;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * CodeMapper
 *
 * @author Zoctan
 * @date 2018/5/27
 */
public interface CodeMapper extends MyMapper<Code> {

    /**
     * 找到用户提交题目的所有代码
     *
     * @param contestId 比赛Id
     * @param problemId 题目Id
     * @param username  用户名
     * @return java.util.List<com.zoctan.api.model.Code>
     */
    List<Code> findAllUserProblemSubmitCode(@Param("contestId") Long contestId, @Param("problemId") Long problemId, @Param("username") String username);


    /**
     * 找到用户提交的所有代码
     *
     * @param contestId 比赛Id
     * @param username  用户名
     * @return java.util.List<com.zoctan.api.model.Code>
     */
    List<Code> findAllUserSubmitCode(@Param("contestId") Long contestId, @Param("username") String username);
}