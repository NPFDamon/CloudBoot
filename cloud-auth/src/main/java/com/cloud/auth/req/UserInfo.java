package com.cloud.auth.req;

import lombok.Data;

import java.io.Serializable;

/**
 * Copyright (C),Damon
 *
 * @Description:
 * @Author: Damon(npf)
 * @Date: 2020-06-30:10:45
 */
@Data
public class UserInfo implements Serializable {
    private String username;
    private String password;
}
