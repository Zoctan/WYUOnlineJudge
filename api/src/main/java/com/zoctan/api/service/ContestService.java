package com.zoctan.api.service;

import com.zoctan.api.core.service.Service;
import com.zoctan.api.model.Contest;

import java.util.List;

/**
 * @author Zoctan
 */
public interface ContestService extends Service<Contest> {
    @Override
    List<Contest> findAll();

    Contest findOne(Long id, String username);
}
