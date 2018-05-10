package com.zoctan.api.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zoctan.api.core.service.AbstractService;
import com.zoctan.api.mapper.PermissionMapper;
import com.zoctan.api.databaseModel.Permission;
import com.zoctan.api.service.PermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class PermissionServiceImpl extends AbstractService<Permission> implements PermissionService {
    @Resource
    private PermissionMapper permissionMapper;

    @Override
    public List<JSONObject> findAllResourcePermission() {
        return this.permissionMapper.findAllResourcePermission();
    }
}
