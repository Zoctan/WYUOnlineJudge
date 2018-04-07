package com.zoctan.api.service;

import com.zoctan.api.core.service.Service;
import com.zoctan.api.model.Code;

import java.util.List;

public interface CodeService extends Service<Code> {
    List<Code> findAllUserProblemSubmitCode(Long problemId, String username);
}
