package com.cloud.auth.rust;

import com.cloud.auth.entity.UserEntity;
import com.cloud.auth.mapper.UserMapper;
import com.cloud.auth.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Copyright (C),Damon
 *
 * @Description:
 * @Author: Damon(npf)
 * @Date: 2020-06-27:10:31
 */
@RestController
@RequestMapping(value = "/auth")
public class LoginController {

    @Autowired
    private TestService testService;

    @GetMapping(value = "/login")
    public String login() {
        return "test";
    }

    @GetMapping(value = "/test")
    public String test() {
        return "test-login";
    }

    @GetMapping(value = "get-user")
    public List<UserEntity> getUser() {
        return testService.getUser();
    }
}
