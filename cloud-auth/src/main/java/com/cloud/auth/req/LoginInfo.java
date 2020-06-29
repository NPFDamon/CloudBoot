package com.cloud.auth.req;

import lombok.Data;

import java.io.Serializable;

/**
 * Copyright (C),Damon
 *
 * @Description:
 * @Author: Damon(npf)
 * @Date: 2020-06-29:16:07
 */
@Data
public class LoginInfo implements Serializable {
    private String username;
    private String password;
}
