package com.zoctan.api.service;

import com.zoctan.api.core.service.Service;
import com.zoctan.api.model.UserContest;

/**
 * @author Zoctan
 */
public interface UserContestService extends Service<UserContest> {
    void save(Long id, String username);

    void delete(Long id, String username);

}
