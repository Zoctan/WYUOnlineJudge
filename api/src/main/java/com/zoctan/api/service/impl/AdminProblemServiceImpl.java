package com.zoctan.api.service.impl;

import com.zoctan.api.mapper.AdminProblemMapper;
import com.zoctan.api.model.AdminProblem;
import com.zoctan.api.service.AdminProblemService;
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
public class AdminProblemServiceImpl extends AbstractService<AdminProblem> implements AdminProblemService {
    @Resource
    private AdminProblemMapper adminProblemMapper;

}
