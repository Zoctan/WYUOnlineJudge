package com.zoctan.api.mapper;

import com.zoctan.api.core.mapper.MyMapper;
import com.zoctan.api.databaseModel.Resource;
import com.zoctan.api.databaseModel.Role;

import java.util.List;

public interface RoleMapper extends MyMapper<Role> {
    List<Resource> findAllRoleWithPermission();
}