package com.zoctan.api.service.impl;

import com.zoctan.api.mapper.ContestMapper;
import com.zoctan.api.model.Contest;
import com.zoctan.api.service.ContestService;
import com.zoctan.api.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
* Created by Zoctan on 2018/03/29.
*/
@Service
@Transactional
@SuppressWarnings("SpringJavaAutowiringInspection")
public class ContestServiceImpl extends AbstractService<Contest> implements ContestService {
    @Resource
    private ContestMapper contestMapper;

}
