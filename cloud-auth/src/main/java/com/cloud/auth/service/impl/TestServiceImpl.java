package com.cloud.auth.service.impl;

import com.cloud.auth.entity.UserEntity;
import com.cloud.auth.mapper.UserMapper;
import com.cloud.auth.req.UserInfo;
import com.cloud.auth.service.TestService;
import com.cloud.common.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Copyright (C),Damon
 *
 * @Description:
 * @Author: Damon(npf)
 * @Date: 2020-06-29:11:11
 */
@Component
public class TestServiceImpl implements TestService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<UserEntity> getUser() {
        return userMapper.getUser();
    }

    @Override
    public UserEntity getUserByName(String username) {
        return userMapper.getUserByUserName(username);
    }

    @Override
    @Transactional(rollbackFor = BizException.class)
    public int register(UserInfo userInfo) {
        UserEntity entity = new UserEntity();
        entity.setUsername(userInfo.getUsername());
        entity.setPassword(userInfo.getPassword());
        return userMapper.insert(entity);
    }
}
