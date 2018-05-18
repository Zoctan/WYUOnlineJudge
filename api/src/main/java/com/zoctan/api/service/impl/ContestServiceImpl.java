package com.zoctan.api.service.impl;

import com.zoctan.api.core.service.AbstractService;
import com.zoctan.api.mapper.ContestMapper;
import com.zoctan.api.mapper.UserContestMapper;
import com.zoctan.api.mapper.UserMapper;
import com.zoctan.api.model.Contest;
import com.zoctan.api.model.User;
import com.zoctan.api.model.UserContest;
import com.zoctan.api.service.ContestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Zoctan
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ContestServiceImpl extends AbstractService<Contest> implements ContestService {
    @Resource
    private ContestMapper contestMapper;
    @Resource
    private UserContestMapper userContestMapper;
    @Resource
    private UserMapper userMapper;

    @Override
    public List<Contest> findAll() {
        // todo no more for
        final List<Contest> contests = this.contestMapper.selectAll();
        final List<Contest> list = new ArrayList<>();

        for (final Contest contest : contests) {
            final Condition condition = new Condition(UserContest.class);
            condition.createCriteria().andCondition("contest_id = ", contest.getId());
            final int joinCount = this.userContestMapper.selectCountByCondition(condition);

            contest.setJoinCount(joinCount);
            contest.setDescription(null);

            list.add(contest);
        }
        return list;
    }

    @Override
    public Contest findOne(final Long id, final String username) {
        final Contest contest = this.contestMapper.selectByPrimaryKey(id);
        final Condition condition = new Condition(UserContest.class);
        condition.createCriteria().andCondition("contest_id = ", id);
        final int joinCount = this.userContestMapper.selectCountByCondition(condition);
        contest.setJoinCount(joinCount);

        final Condition condition2 = new Condition(User.class);
        condition.createCriteria().andCondition("id = ", id);
        final User user = this.userMapper.selectByCondition(condition2).get(0);

        condition.createCriteria().andCondition("user_id = ", user.getId());
        final List<UserContest> list = this.userContestMapper.selectByCondition(condition);
        contest.setJoin(list.size() != 0);
        return contest;
    }
}
