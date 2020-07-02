package com.cloud.auth.server.fegin.inerfaces;

import com.cloud.common.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/login")
public interface AuthFeginService {
    @GetMapping("test")
    Result test();

    @GetMapping("/get-session")
    Result getSession();

    @GetMapping(value = "get-user")
    Result getUser();
}
