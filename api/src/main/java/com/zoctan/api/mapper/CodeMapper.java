package com.zoctan.api.mapper;

import com.zoctan.api.core.mapper.MyMapper;
import com.zoctan.api.databaseModel.Code;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CodeMapper extends MyMapper<Code> {
    List<Code> findAllUserProblemSubmitCode(@Param("problemId") Long problemId, @Param("username") String username);
}