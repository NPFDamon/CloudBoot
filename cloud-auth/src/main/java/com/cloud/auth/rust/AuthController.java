package com.cloud.auth.rust;

import com.cloud.auth.req.UserInfo;
import com.cloud.auth.service.TestService;
import com.cloud.common.result.Result;
import com.cloud.common.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright (C),Damon
 *
 * @Description:
 * @Author: Damon(npf)
 * @Date: 2020-06-30:10:44
 */
@RestController
@Slf4j
@RequestMapping(value = "/auth")
public class AuthController {
    @Autowired
    private TestService testService;

    @PostMapping(value = "register")
    public Result register(UserInfo userInfo) {
        int res = testService.register(userInfo);
        if (res == 1) {
            return Result.success("register success");
        } else {
            return Result.failure(ResultCode.REGISTER_ERROR, ResultCode.REGISTER_ERROR.getMsg());
        }
    }
}
