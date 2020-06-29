package com.cloud.auth.service;

import com.cloud.auth.entity.UserEntity;

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
}
