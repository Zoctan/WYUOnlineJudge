package com.zoctan.api.service.impl;

import com.zoctan.api.mapper.ProblemMapper;
import com.zoctan.api.model.Problem;
import com.zoctan.api.service.ProblemService;
import com.zoctan.api.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
* Created by Zoctan on 2018/03/28.
*/
@Service
@Transactional
@SuppressWarnings("SpringJavaAutowiringInspection")
public class ProblemServiceImpl extends AbstractService<Problem> implements ProblemService {
    @Resource
    private ProblemMapper problemMapper;

}
