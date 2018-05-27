package com.zoctan.api.mapper;

import com.zoctan.api.core.mapper.MyMapper;
import com.zoctan.api.model.RolePermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Zoctan
 * @date 2018/5/27
 */
public interface RolePermissionMapper extends MyMapper<RolePermission> {
    /**
     * 保存角色，包括对应的权限
     *
     * @param roleId           角色Id
     * @param permissionIdList 权限Id列表
     */
    void saveRolePermission(@Param("roleId") Object roleId, @Param("permissionIdList") List<Integer> permissionIdList);
}