package com.zoctan.api.service.impl;

import com.zoctan.api.core.service.AbstractService;
import com.zoctan.api.mapper.ContestProblemMapper;
import com.zoctan.api.model.ContestProblem;
import com.zoctan.api.service.ContestProblemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author Zoctan
 * @date 2018/5/27
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ContestProblemServiceImpl extends AbstractService<ContestProblem> implements ContestProblemService {
    @Resource
    private ContestProblemMapper contestProblemMapper;

}
