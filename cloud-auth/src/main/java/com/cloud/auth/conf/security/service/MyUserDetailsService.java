package com.cloud.auth.conf.security.service;

import com.cloud.auth.conf.security.entity.User;
import com.cloud.auth.entity.UserEntity;
import com.cloud.auth.mapper.UserMapper;
import com.cloud.common.exception.BizException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Copyright (C),Damon
 *
 * @Description:
 * @Author: Damon(npf)
 * @Date: 2020-06-27:12:29
 */
@Component
@Slf4j
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserEntity entity = userMapper.getUserByUserName(s);
        if (entity == null) {
            throw new UsernameNotFoundException("user is not exit or password is wrong");
        }
        return new User(entity.getUsername(), passwordEncoder.encode(entity.getPassword()), AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));
    }
}
