package com.zoctan.api.mapper;

import com.zoctan.api.core.mapper.MyMapper;
import com.zoctan.api.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author Zoctan
 * @date 2018/5/27
 */
public interface UserMapper extends MyMapper<User> {
    /**
     * 获取所有用户，包括其角色
     *
     * @return 用户列表
     */
    List<User> findAllUserWithRole();

    /**
     * 获取用户
     *
     * @param param 　查询参数
     * @return 用户
     */
    User findDetailBy(Map<String, Object> param);

    /**
     * 更新用户登录时间
     *
     * @param username 用户名
     */
    void updateLastLoginTimeByUsername(@Param("username") String username);
}