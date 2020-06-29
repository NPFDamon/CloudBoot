package com.cloud.auth.conf.mybatis;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Copyright (C),Damon
 *
 * @Description:
 * @Author: Damon(npf)
 * @Date: 2020-06-29:10:57
 */
@Configuration
@EnableTransactionManagement
public class DataSourceConf {

//    @Configuration
//    @Profile("dev")
//    @PropertySource("")
//    static class Dev{}
//
//    @Configuration
//    @Profile("pre")
//    @PropertySource("")
//    static class Pre{}
//
//    @Configuration
//    @Profile("pro")
//    @PropertySource("")
//    static class Pro{}
}
