package com.cloud.auth.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Copyright (C),Damon
 *
 * @Description:
 * @Author: Damon(npf)
 * @Date: 2020-06-29:10:25
 */
@Data
@TableName("cloud_user")
public class UserEntity implements Serializable {
    @TableField("id")
    private Long id;
    @TableField("username")
    private String username;
    @TableField("password")
    private String password;
    @TableField("create_date")
    private Date createDate;
    @TableField("update_date")
    private Date updateDate;
}
