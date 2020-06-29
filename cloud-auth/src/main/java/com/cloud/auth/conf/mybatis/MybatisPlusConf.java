package com.cloud.auth.conf.mybatis;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.MybatisXMLLanguageDriver;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;

/**
 * Copyright (C),Damon
 *
 * @Description:
 * @Author: Damon(npf)
 * @Date: 2020-06-29:14:26
 */
@Configuration
@AutoConfigureAfter(DataSourceProperties.class)
@EnableConfigurationProperties(MybatisProperties.class)
public class MybatisPlusConf {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private MybatisProperties mybatisProperties;

    @Autowired
    private ResourceLoader resourceLoader = new DefaultResourceLoader();

    @Autowired(required = false)
    private Interceptor[] interceptors;

    @Autowired(required = false)
    private DatabaseIdProvider databaseIdProvider;

    /**
     * 分页
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setDialectType("mysql");
        return paginationInterceptor;
    }

    @Bean
    public MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean(){
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setVfs(SpringBootVFS.class);
        if(StringUtils.hasText(this.mybatisProperties.getConfigLocation())){
            bean.setConfigLocation(this.resourceLoader.getResource(this.mybatisProperties.getConfigLocation()));
        }

        bean.setConfiguration((MybatisConfiguration) mybatisProperties.getConfiguration());
        if(ObjectUtils.isEmpty(this.interceptors)){
            bean.setPlugins(this.interceptors);
        }

//        GlobalConfig config = new GlobalConfig();
//        GlobalConfig.DbConfig dbConfig = new GlobalConfig.DbConfig();
//        dbConfig.setIdType()
//        config.setDbConfig()

       MybatisConfiguration mybatisConfiguration = new MybatisConfiguration();
        mybatisConfiguration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
        bean.setConfiguration(mybatisConfiguration);
        if (this.databaseIdProvider != null) {
            bean.setDatabaseIdProvider(this.databaseIdProvider);
        }
        if (StringUtils.hasLength(this.mybatisProperties.getTypeAliasesPackage())) {
            bean.setTypeAliasesPackage(this.mybatisProperties.getTypeAliasesPackage());
        }
        if (StringUtils.hasLength(this.mybatisProperties.getTypeHandlersPackage())) {
            bean.setTypeHandlersPackage(this.mybatisProperties.getTypeHandlersPackage());
        }
        if (!ObjectUtils.isEmpty(this.mybatisProperties.resolveMapperLocations())) {
            bean.setMapperLocations(this.mybatisProperties.resolveMapperLocations());
        }
        return bean;
    }

}
