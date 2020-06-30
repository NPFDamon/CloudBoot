package com.cloud.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloud.auth.entity.UserEntity;
import com.cloud.auth.req.UserInfo;

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
