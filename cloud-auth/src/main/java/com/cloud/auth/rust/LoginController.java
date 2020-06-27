package com.cloud.auth.rust;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping(value = "/login")
    public String login() {
        return "test";
    }

    @GetMapping(value = "/test")
    public String test() {
        return "test-login";
    }
}
