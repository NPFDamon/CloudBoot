package com.cloud.auth.rust;

import com.cloud.auth.entity.UserEntity;
import com.cloud.auth.req.LoginInfo;
import com.cloud.auth.service.TestService;
import com.cloud.common.result.Result;
import com.cloud.common.result.ResultCode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping(value = "/login")
    @ResponseBody
    public Result login(@RequestBody LoginInfo loginInfo, HttpSession session) {
        return Result.success();
    }

    @GetMapping(value = "/test")
    @ResponseBody
    public Result test() {
        return Result.success("test success");
    }

    @GetMapping(value = "get-user")
    @ResponseBody
    public Result getUser() throws JsonProcessingException {
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
