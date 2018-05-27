package com.zoctan.api.service.impl;

import com.zoctan.api.core.service.AbstractService;
import com.zoctan.api.mapper.UserContestMapper;
import com.zoctan.api.mapper.UserMapper;
import com.zoctan.api.model.User;
import com.zoctan.api.model.UserContest;
import com.zoctan.api.service.UserContestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;

/**
 * @author Zoctan
 * @date 2018/5/27
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserContestServiceImpl extends AbstractService<UserContest> implements UserContestService {
    @Resource
    private UserContestMapper userContestMapper;
    @Resource
    private UserMapper userMapper;

    @Override
    public void save(final String username, final Long contestId) {
        final Condition condition = new Condition(User.class);
        condition.createCriteria().andCondition("username = ", username);
        final User user = this.userMapper.selectByCondition(condition).get(0);
        final UserContest userContest = new UserContest();
        userContest.setContestIdId(contestId);
        userContest.setUserId(user.getId());
        this.userContestMapper.insert(userContest);
    }

    @Override
    public void delete(final String username, final Long contestId) {
        this.userContestMapper.deleteByUsernameAndContestId(username, contestId);
    }
}
