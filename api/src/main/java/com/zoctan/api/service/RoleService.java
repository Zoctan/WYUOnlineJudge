package com.zoctan.api.service;

import com.zoctan.api.core.service.Service;
import com.zoctan.api.model.Resource;
import com.zoctan.api.model.Role;

import java.util.List;

/**
 * @author Zoctan
 * @date 2018/5/27
 */
public interface RoleService extends Service<Role> {
    /**
     * 获取所有角色，包括其对应的权限
     *
     * @return 角色资源列表
     */
    List<Resource> findAllRoleWithPermission();

    /**
     * 保存角色
     *
     * @param role 角色
     */
    @Override
    void save(final Role role);

    /**
     * 更新角色
     *
     * @param role 角色
     */
    @Override
    void update(Role role);
}
