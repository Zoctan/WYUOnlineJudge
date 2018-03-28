package com.zoctan.api.service.impl;

import com.zoctan.api.mapper.CodeMapper;
import com.zoctan.api.model.Code;
import com.zoctan.api.service.CodeService;
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
public class CodeServiceImpl extends AbstractService<Code> implements CodeService {
    @Resource
    private CodeMapper codeMapper;

}
