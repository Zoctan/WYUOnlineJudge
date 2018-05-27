package com.zoctan.api.service.impl;

import com.zoctan.api.core.service.AbstractService;
import com.zoctan.api.mapper.HelpMapper;
import com.zoctan.api.model.Help;
import com.zoctan.api.service.HelpService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author Zoctan
 * @date 2018/5/27
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class HelpServiceImpl extends AbstractService<Help> implements HelpService {
    @Resource
    private HelpMapper helpMapper;

}
