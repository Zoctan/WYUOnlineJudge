package com.zoctan.api.service.impl;

import com.zoctan.api.core.service.AbstractService;
import com.zoctan.api.mapper.AdminProblemMapper;
import com.zoctan.api.databaseModel.AdminProblem;
import com.zoctan.api.service.AdminProblemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class AdminProblemServiceImpl extends AbstractService<AdminProblem> implements AdminProblemService {
    @Resource
    private AdminProblemMapper adminProblemMapper;

}
