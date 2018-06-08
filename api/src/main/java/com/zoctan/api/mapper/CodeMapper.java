package com.zoctan.api.mapper;

import com.zoctan.api.core.mapper.MyMapper;
import com.zoctan.api.model.Code;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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
     * @param map 含参数的map
     * @return java.util.List<com.zoctan.api.model.Code>
     */
    List<Code> findSubmitCodeByUsername(Map<String, Object> map);

}