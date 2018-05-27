package com.zoctan.api.service;

import com.zoctan.api.core.service.Service;
import com.zoctan.api.model.Code;

import java.util.List;

/**
 * @author Zoctan
 * @date 2018/5/27
 */
public interface CodeService extends Service<Code> {
    /**
     * 获取用户提交的相关问题的代码
     *
     * @param contestId 　比赛Id
     * @param problemId 题目Id
     * @param username  用户名
     * @return 用户提交的相关问题的代码
     */
    List<Code> findAllUserProblemSubmitCode(Long contestId, Long problemId, String username);

    /**
     * 获取用户提交的所有代码
     *
     * @param contestId 　比赛Id
     * @param username  用户名
     * @return 用户提交的所有代码
     */
    List<Code> findAllUserSubmitCode(Long contestId, String username);
}
