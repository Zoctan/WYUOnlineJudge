package com.zoctan.api.service.impl;

import com.zoctan.api.core.service.AbstractService;
import com.zoctan.api.mapper.CodeMapper;
import com.zoctan.api.databaseModel.Code;
import com.zoctan.api.service.CodeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class CodeServiceImpl extends AbstractService<Code> implements CodeService {
    @Resource
    private CodeMapper codeMapper;

    @Override
    public List<Code> findAllUserProblemSubmitCode(final Long problemId, final String username) {
        return this.codeMapper.findAllUserProblemSubmitCode(problemId, username);
    }
}
