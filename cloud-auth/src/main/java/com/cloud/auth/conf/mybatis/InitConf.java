package com.cloud.auth.conf.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * Copyright (C),Damon
 *
 * @Description:
 * @Author: Damon(npf)
 * @Date: 2020-06-29:14:25
 */
@Configuration
@MapperScan("com.cloud.auth.mapper")
public class InitConf {
}
