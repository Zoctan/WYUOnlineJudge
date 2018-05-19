package com.zoctan.api.service.impl;

import com.zoctan.api.mapper.ContestProblemMapper;
import com.zoctan.api.model.ContestProblem;
import com.zoctan.api.service.ContestProblemService;
import com.zoctan.api.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
* Created by Zoctan on 2018/05/19.
*/
@Service
@Transactional
@SuppressWarnings("SpringJavaAutowiringInspection")
public class ContestProblemServiceImpl extends AbstractService<ContestProblem> implements ContestProblemService {
    @Resource
    private ContestProblemMapper contestProblemMapper;

}
