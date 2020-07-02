package com.cloud.auth.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloud.auth.server.entity.UserEntity;

import java.util.List;

/**
 * Copyright (C),Damon
 *
 * @Description:
 * @Author: Damon(npf)
 * @Date: 2020-06-29:10:27
 */
public interface UserMapper extends BaseMapper<UserEntity> {
    UserEntity getUserByUserName(String userName);
    List<UserEntity> getUser();
}
