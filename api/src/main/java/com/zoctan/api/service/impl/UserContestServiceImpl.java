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
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserContestServiceImpl extends AbstractService<UserContest> implements UserContestService {
    @Resource
    private UserContestMapper userContestMapper;
    @Resource
    private UserMapper userMapper;

    private UserContest get(final Long id, final String username) {
        final Condition condition = new Condition(User.class);
        condition.createCriteria().andCondition("username = ", username);
        final User user = this.userMapper.selectByCondition(condition).get(0);
        final UserContest userContest = new UserContest();
        userContest.setContestIdId(id);
        userContest.setUserId(user.getId());
        return userContest;
    }

    @Override
    public void save(final Long id, final String username) {
        final UserContest userContest = this.get(id, username);
        this.userContestMapper.insert(userContest);
    }

    @Override
    public void delete(final Long id, final String username) {
        final UserContest userContest = this.get(id, username);
        this.userContestMapper.delete(userContest);
    }
}
