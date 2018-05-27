package com.zoctan.api.service;

import com.alibaba.fastjson.JSONObject;
import com.zoctan.api.core.service.Service;
import com.zoctan.api.model.Permission;

import java.util.List;

/**
 * @author Zoctan
 * @date 2018/5/27
 */
public interface PermissionService extends Service<Permission> {
    /**
     * 资源对应的权限操作
     *
     * @return 资源权限列表
     */
    List<JSONObject> findAllResourcePermission();
}
