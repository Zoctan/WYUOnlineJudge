package com.zoctan.api.mapper;

import com.zoctan.api.core.mapper.MyMapper;
import com.zoctan.api.model.Code;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * CodeMapper
 *
 * @author Zoctan
 * @date 5/13/18 2:39 PM
 */
public interface CodeMapper extends MyMapper<Code> {

    /**
     * 找到用户提交的所有代码
     *
     * @param problemId 题目Id
     * @param username  用户名
     * @return java.util.List<com.zoctan.api.model.Code>
     * @author Zoctan
     * @date 5/13/18 2:39 PM
     */
    List<Code> findAllUserProblemSubmitCode(@Param("problemId") Long problemId, @Param("username") String username);
}