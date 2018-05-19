package com.zoctan.api.service.impl;

import com.zoctan.api.core.service.AbstractService;
import com.zoctan.api.mapper.CodeMapper;
import com.zoctan.api.model.Code;
import com.zoctan.api.service.CodeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Zoctan
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CodeServiceImpl extends AbstractService<Code> implements CodeService {
    @Resource
    private CodeMapper codeMapper;

    @Override
    public List<Code> findAllUserProblemSubmitCode(final Long contestId, final Long problemId, final String username) {
        return this.codeMapper.findAllUserProblemSubmitCode(contestId, problemId, username);
    }

    @Override
    public List<Code> findAllUserSubmitCode(final Long contestId, final String username) {
        return this.codeMapper.findAllUserSubmitCode(contestId, username);
    }
}
