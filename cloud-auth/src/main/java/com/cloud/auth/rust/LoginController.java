package com.cloud.auth.rust;

import com.cloud.auth.entity.UserEntity;
import com.cloud.auth.req.LoginInfo;
import com.cloud.auth.service.TestService;
import com.cloud.common.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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

    @PostMapping(value = "/login")
    public Result login(@RequestBody LoginInfo loginInfo, HttpSession session) {
        return Result.success();
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
