package com.zoctan.api.service.impl;

import com.zoctan.api.mapper.HelpMapper;
import com.zoctan.api.model.Help;
import com.zoctan.api.service.HelpService;
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
public class HelpServiceImpl extends AbstractService<Help> implements HelpService {
    @Resource
    private HelpMapper helpMapper;

}
