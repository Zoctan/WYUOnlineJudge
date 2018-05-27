package com.zoctan.api.mapper;

import com.zoctan.api.core.mapper.MyMapper;
import com.zoctan.api.model.Resource;
import com.zoctan.api.model.Role;

import java.util.List;

/**
 * @author Zoctan
 * @date 2018/5/27
 */
public interface RoleMapper extends MyMapper<Role> {
    /**
     * 获取所有角色，包括其对应的权限
     *
     * @return 角色资源列表
     */
    List<Resource> findAllRoleWithPermission();
}