package com.zoctan.api.service;

import com.zoctan.api.core.service.Service;
import com.zoctan.api.databaseModel.User;
import com.zoctan.api.databaseModel.UserRole;

public interface UserRoleService extends Service<UserRole> {
    void updateUserRole(User user);
}
