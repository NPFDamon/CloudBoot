package com.cloud.auth.service.impl;

import com.cloud.auth.entity.UserEntity;
import com.cloud.auth.mapper.UserMapper;
import com.cloud.auth.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.text.html.parser.Entity;
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

    @Override
    public List<UserEntity> getUser() {
        return userMapper.getUser();
    }

    @Override
    public UserEntity getUserByName(String username) {
        return userMapper.getUserByUserName(username);
    }
}
