package com.zoctan.api.service.impl;

import com.zoctan.api.core.service.AbstractService;
import com.zoctan.api.mapper.ContestMapper;
import com.zoctan.api.mapper.UserContestMapper;
import com.zoctan.api.model.Contest;
import com.zoctan.api.model.UserContest;
import com.zoctan.api.service.ContestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Zoctan
 * @date 2018/5/27
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ContestServiceImpl extends AbstractService<Contest> implements ContestService {
    @Resource
    private ContestMapper contestMapper;
    @Resource
    private UserContestMapper userContestMapper;

    @Override
    public List<Contest> findAll() {
        return this.contestMapper.findAll();
    }

    @Override
    public Contest findOne(final Long contestId, final String username) {
        final Contest contest = this.contestMapper.findOne(contestId);

        final List<UserContest> list = this.userContestMapper.findBy(username, contestId);
        contest.setJoin(list.size() != 0);
        return contest;
    }
}
