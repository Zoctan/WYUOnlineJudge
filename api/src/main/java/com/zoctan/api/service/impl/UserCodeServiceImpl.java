package com.zoctan.api.service.impl;

import com.zoctan.api.core.service.AbstractService;
import com.zoctan.api.mapper.UserCodeMapper;
import com.zoctan.api.model.UserCode;
import com.zoctan.api.service.UserCodeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class UserCodeServiceImpl extends AbstractService<UserCode> implements UserCodeService {
    @Resource
    private UserCodeMapper userCodeMapper;

}
