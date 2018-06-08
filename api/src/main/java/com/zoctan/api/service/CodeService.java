package com.zoctan.api.service;

import com.zoctan.api.core.service.Service;
import com.zoctan.api.model.Code;

import java.util.List;
import java.util.Map;

/**
 * @author Zoctan
 * @date 2018/5/27
 */
public interface CodeService extends Service<Code> {

    /**
     * 找到用户提交题目的所有代码
     *
     * @param map 含参数的map
     * @return java.util.List<com.zoctan.api.model.Code>
     */
    List<Code> findSubmitCodeByUsername(Map<String, Object> map);
}
