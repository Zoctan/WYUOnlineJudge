package com.zoctan.api.mapper;

import com.alibaba.fastjson.JSONObject;
import com.zoctan.api.core.mapper.MyMapper;
import com.zoctan.api.model.Permission;

import java.util.List;

/**
 * @author Zoctan
 * @date 2018/5/27
 */
public interface PermissionMapper extends MyMapper<Permission> {
    /**
     * 资源对应的权限操作
     * [{resource: 用户, {[id: 1, handle: 列表], [id: 2, handle: 添加], ...}}
     * ,{resource: xx, {[id: xx, handle: xx], ...}]
     *
     * @return 资源权限列表
     */
    List<JSONObject> findAllResourcePermission();

    /**
     * 所有权限code
     * [user:list, user:add, ...]
     *
     * @return 权限code
     */
    List<String> findAllCode();
}