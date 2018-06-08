package com.zoctan.api.service.impl;

import com.zoctan.api.core.service.AbstractService;
import com.zoctan.api.mapper.CodeMapper;
import com.zoctan.api.model.Code;
import com.zoctan.api.service.CodeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author Zoctan
 * @date 2018/5/27
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CodeServiceImpl extends AbstractService<Code> implements CodeService {
    @Resource
    private CodeMapper codeMapper;

    @Override
    public List<Code> findSubmitCodeByUsername(Map<String, Object> map) {
        return codeMapper.findSubmitCodeByUsername(map);
    }
}
