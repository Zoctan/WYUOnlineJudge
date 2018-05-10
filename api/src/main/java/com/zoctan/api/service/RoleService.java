package com.zoctan.api.service;

import com.zoctan.api.core.service.Service;
import com.zoctan.api.databaseModel.Resource;
import com.zoctan.api.databaseModel.Role;

import java.util.List;

public interface RoleService extends Service<Role> {
    List<Resource> findAllRoleWithPermission();

    @Override
    void save(final Role role);

    @Override
    void update(Role role);
}
