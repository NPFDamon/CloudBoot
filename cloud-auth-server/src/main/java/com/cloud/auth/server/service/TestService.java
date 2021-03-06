package com.cloud.auth.server.service;

import com.cloud.auth.server.entity.UserEntity;
import com.cloud.auth.server.req.UserInfo;

import java.util.List;

/**
 * Copyright (C),Damon
 *
 * @Description:
 * @Author: Damon(npf)
 * @Date: 2020-06-29:11:11
 */
public interface TestService {
    List<UserEntity> getUser();
    UserEntity getUserByName(String username);
    int register(UserInfo userInfo);
}
