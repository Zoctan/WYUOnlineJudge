package com.zoctan.api.service;

import com.alibaba.fastjson.JSONObject;
import com.zoctan.api.core.service.Service;
import com.zoctan.api.model.Permission;

import java.util.List;

/**
 * @author Zoctan
 */
public interface PermissionService extends Service<Permission> {
    List<JSONObject> findAllResourcePermission();
}
