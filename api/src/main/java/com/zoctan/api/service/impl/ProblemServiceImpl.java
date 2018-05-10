package com.zoctan.api.service.impl;

import com.zoctan.api.core.service.AbstractService;
import com.zoctan.api.mapper.ProblemMapper;
import com.zoctan.api.databaseModel.Problem;
import com.zoctan.api.service.ProblemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class ProblemServiceImpl extends AbstractService<Problem> implements ProblemService {
    @Resource
    private ProblemMapper problemMapper;

}
