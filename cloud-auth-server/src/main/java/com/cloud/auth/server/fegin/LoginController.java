package com.cloud.auth.server.fegin;

import com.cloud.auth.server.entity.UserEntity;
import com.cloud.auth.server.fegin.inerfaces.AuthFeginService;
import com.cloud.auth.server.service.TestService;
import com.cloud.common.result.Result;
import com.cloud.common.result.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Copyright (C),Damon
 *
 * @Description:
 * @Author: Damon(npf)
 * @Date: 2020-06-27:10:31
 */
@RestController
@RequestMapping(value = "/login")
public class LoginController implements AuthFeginService {

    @Autowired
    private TestService testService;


    @GetMapping(value = "/test")
    @ResponseBody
    public Result test() {
        return Result.success("test success");
    }

    @GetMapping(value = "get-user")
    @ResponseBody
    public Result getUser(){
        List<UserEntity> entities = testService.getUser();
        if (entities != null) {
            return Result.success(entities);
        }
        return Result.failure(ResultCode.COMMON_SERVER_ERROR);
    }

    @GetMapping(value = "get-session")
    @ResponseBody
    public Result getSession() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            return Result.success(authentication);
        } else {
            return Result.failure(ResultCode.COMMON_SERVER_ERROR, "get session fail");

        }
    }
    @GetMapping(value = "session-invalid")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public Result sessionInvalid(){
        return Result.success("session invalid");
    }
}
